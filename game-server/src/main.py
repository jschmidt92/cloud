import os
import subprocess
import requests
import json
import shutil

# Load environment variables from the .env file
from dotenv import load_dotenv

# Load environment variables from the .env file
load_dotenv(".env")

# Access the environment variables using os.environ
ARMA3_PATH = os.environ.get("ARMA3_PATH")
APP_ID = os.environ.get("APP_ID")
COLLECTION_ID = os.environ.get("COLLECTION_ID")
REPO_PATH = os.environ.get("REPO_PATH")
STEAM_API_KEY = os.environ.get("STEAM_API_KEY")
STEAM_PASSWORD = (os.environ.get("STEAM_PASSWORD"),)
STEAM_USERNAME = (os.environ.get("STEAM_USERNAME"),)
STEAM_WORKSHOP_API_URL = os.environ.get("STEAM_WORKSHOP_API_URL")
STEAMCMD_PATH = os.environ.get("STEAMCMD_PATH")


def download_addons(COLLECTION_ID, APP_ID, STEAMCMD_PATH, REPO_PATH):
    # Send a POST request to the workshop API with the collection ID
    response = requests.post(
        STEAM_WORKSHOP_API_URL,
        data={
            "key": STEAM_API_KEY,
            "collectioncount": 1,
            "publishedfileids[0]": COLLECTION_ID,
        },
    )

    if response.status_code != 200:
        print(
            f"Failed to get collection details from Steam API. Status code: {response.status_code}"
        )
        print(f"Response content: {response.content}")
        return

    try:
        data = response.json()
    except json.decoder.JSONDecodeError:
        print("Failed to parse JSON response from Steam API.")
        print(f"Response content: {response.content}")
        return

    if data["response"]["result"] != 1:
        print("Failed to get collection details from Steam API.")
        return

    file_ids = [
        file["publishedfileid"]
        for file in data["response"]["collectiondetails"][0]["children"]
    ]

    # Run SteamCMD to download the addons
    for file_id in file_ids:
        subprocess.call(
            [
                STEAMCMD_PATH,
                "+force_install_dir",
                ARMA3_PATH,
                "+login",
                STEAM_USERNAME,
                STEAM_PASSWORD,
                "+workshop_download_item",
                APP_ID,
                file_id,
                "+quit",
            ]
        )

    # Move downloaded mod folders to the repo folder
    for file_id in file_ids:
        mod_folder = os.path.join(
            ARMA3_PATH, "steamapps", "workshop", "content", APP_ID, file_id
        )
        if os.path.exists(mod_folder):
            shutil.move(mod_folder, REPO_PATH)

    print("Addons downloaded and moved to the repo folder successfully.")


download_addons(COLLECTION_ID, APP_ID, STEAMCMD_PATH, REPO_PATH)
