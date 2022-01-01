#!/bin/sh

# Install the jq Javascript parsing utility

sudo apt-get install -y jq

# Install Java

sudo apt update -y && sudo apt install default-jdk -y && sudo apt install tree -y

# Install Lienengen

wget https://raw.githubusercontent.com/technomancy/leiningen/stable/bin/lein

chmod +x lein

sudo mv lein /usr/local/bin

# Install Node

curl -sL https://deb.nodesource.com/setup_current.x | sudo -E bash -

sudo apt-get install -y nodejs

# Install the data genearator

cd ~/

git clone https://github.com/reselbob/datagen.git

cd ./datagen/big-data-json/

npm install

chmod +x data-gen.js

# Create a file named big-data.json that has an array of 2 million JSON objects

./data-gen.js 2

# Copy the file to ~/data/big-data.json

mkdir ~/data && mv big-data.json ~/data/big-data.json
