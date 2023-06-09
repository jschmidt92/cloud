FROM python:slim

# Set the working directory
WORKDIR /app

# Copy the requirements.txt file
COPY requirements.txt .

# Update and Install dependencies
RUN apt-get update && \
  apt-get install -y curl && \
  curl -sL https://deb.nodesource.com/setup_20.x | bash - && \
  apt-get install -y nodejs locales

# Set the locale
RUN sed -i '/en_US.UTF-8/s/^# //g' /etc/locale.gen && \
  locale-gen

# Set environment variables
ENV LANG en_US.UTF-8
ENV LANGUAGE en_US:en
ENV LC_ALL en_US.UTF-8
ENV REDIS_PASSWORD='12345'

# Install the required Python packages
RUN pip3 install --no-cache-dir -r requirements.txt

# Copy the src folder
COPY ./src ./src
