FROM ubuntu:latest

COPY requirements.txt .

SHELL ["/bin/bash", "-o", "pipefail", "-c"]
RUN apt-get update && \
  apt-get install -y --no-install-recommends \
  ca-certificates \
  lib32gcc-s1 \
  libcurl4 \
  locales \
  net-tools \
  python3 \
  python3-pip \
  wget && \
  rm -rf /var/lib/apt/lists/*

RUN sed -i '/en_US.UTF-8/s/^# //g' /etc/locale.gen && \
  locale-gen

ENV ARMA_BINARY=./arma3server
ENV ARMA_CDLC=
ENV ARMA_CONFIG=main.cfg
ENV ARMA_LIMITFPS=1000
ENV ARMA_PARAMS=
ENV ARMA_PROFILE=main
ENV ARMA_WORLD=empty
ENV LANG=en_US.UTF-8
ENV LANGUAGE=en_US:en
ENV LC_ALL=en_US.UTF-8
ENV MODS_LOCAL=true
ENV MODS_PRESET=
ENV PORT=2302
ENV SKIP_INSTALL=false
ENV STEAM_BRANCH=public
ENV STEAM_BRANCH_PASSWORD=

RUN pip3 install --no-cache-dir -r requirements.txt

RUN mkdir -p /steamcmd && \
  wget -qO- https://steamcdn-a.akamaihd.net/client/installer/steamcmd_linux.tar.gz | tar zxf - -C /steamcmd

EXPOSE 2302-2306/udp
EXPOSE 2302-2306/tcp
EXPOSE 2402-2406/udp
EXPOSE 2402-2406/tcp

WORKDIR /arma3

VOLUME /steamcmd

STOPSIGNAL SIGINT

COPY *.py /

CMD ["python3","/launch.py"]