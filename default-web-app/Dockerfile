FROM python:slim

WORKDIR /app

COPY requirements.txt .

RUN apt-get update && \
  apt-get install -y curl && \
  curl -sL https://deb.nodesource.com/setup_20.x | bash - && \
  apt-get install -y nodejs locales

RUN sed -i '/en_US.UTF-8/s/^# //g' /etc/locale.gen && \
  locale-gen

ENV LANG en_US.UTF-8
ENV LANGUAGE en_US:en
ENV LC_ALL en_US.UTF-8
ENV REDIS_PASSWORD='12345'

RUN pip3 install --no-cache-dir -r requirements.txt

COPY ./src ./src
