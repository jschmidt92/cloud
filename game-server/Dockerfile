FROM ubuntu:latest

# Install dependencies
RUN apt-get update && \
    apt-get install -y --no-install-recommends --no-install-suggests \
        software-properties-common \
        wget \
        ca-certificates \
        lib32gcc-s1 \
        lib32stdc++6 \
        locales \
        net-tools \
        python3 \
        python3-pip && \
    rm -rf /var/lib/apt/lists/*

# Enable the en_US.UTF-8 locale
RUN sed -i '/en_US.UTF-8/s/^# //g' /etc/locale.gen && \
    locale-gen

# Download and install SteamCMD
RUN mkdir -p /steamcmd && \
    wget -qO- 'https://steamcdn-a.akamaihd.net/client/installer/steamcmd_linux.tar.gz' | tar zxf - -C /steamcmd

# Install Python dependencies
COPY requirements.txt .
RUN pip3 install -r requirements.txt

# Docker environment values
ENV ARMA_BINARY=./arma3server
ENV ARMA_CONFIG=main.cfg
ENV ARMA_PARAMS=
ENV ARMA_PROFILE=main
ENV ARMA_WORLD=empty
ENV ARMA_LIMITFPS=1000
ENV ARMA_CDLC=
ENV HEADLESS_CLIENTS=0
ENV HEADLESS_CLIENTS_PROFILE="\$profile-hc-\$i"
ENV PORT=2302
ENV STEAM_BRANCH=public
ENV STEAM_BRANCH_PASSWORD=
ENV MODS_LOCAL=true
ENV MODS_PRESET=
ENV SKIP_INSTALL=false

# Expose necessary ports
EXPOSE 2302/udp
EXPOSE 2303/udp
EXPOSE 2304/udp
EXPOSE 2305/udp
EXPOSE 2306/udp

# Set working directory
WORKDIR /arma3

# Mount the steamcmd volume
VOLUME /steamcmd

# Define the stop signal
STOPSIGNAL SIGINT

# Copy the Python scripts to the container
COPY *.py /

# Set the entrypoint to run the Python script
CMD ["python3", "/launch.py"]