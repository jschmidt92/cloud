from fastapi import FastAPI, Query
from pydantic import BaseModel
from typing import Optional

import json
import redis

r = redis.Redis(host="redis", port=6380)
app = FastAPI()

# import debugpy

# debugpy.listen(('0.0.0.0', 5678))
# print('Waiting for client to attach...')
# debugpy.wait_for_client()


# @app.get('/')
# def read_root():
#     return {'Hello': 'World!'}


# @app.get('/hits')
# def read_root():
#     r.incr('hits')
#     return {'Number of hits': r.get('hits')}


class User(BaseModel):
    id: Optional[int] = None
    name: str
    age: int
    gender: str


with open("users.json", "r") as f:
    users = json.load(f)["users"]


@app.get("/user/{u_id}", status_code=200)
def get_user(u_id: int):
    user = [u for u in users if u["id"] == u_id]
    return user[0] if len(user) > 0 else {}


@app.get("/search", status_code=200)
def search_user(age: Optional[int] = Query(None), name: Optional[str] = Query(None)):
    users_by_age = [u for u in users if u["age"] == age]

    if name is None:
        if age is None:
            return users
        else:
            return users_by_age
    else:
        users_by_name = [u for u in users if name.lower() in u["name"].lower()]
        if age is None:
            return users_by_name
        else:
            combined = [u for u in users if u in users_by_name]
            return combined


@app.post("/addUser", status_code=201)
def add_user(user: User):
    u_id = max([u["id"] for u in users]) + 1
    new_user = {"id": u_id, "name": user.name, "age": user.age, "gender": user.gender}

    users.append(new_user)

    with open("users.json", "w") as f:
        json.dump(users, f)

    return new_user
