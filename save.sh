#/bin/bash

GIT_EMAIL=$(git config --global user.email)
GIT_USER_NAME=$(git config --global user.name)
GIT_PASSWORD=$(git config --global user.password)

if [ -z $GIT_EMAIL ]
then
    read -p "Enter a git email: " GIT_EMAIL
    
    while [[ -z $GIT_EMAIL ]]; do
    read -p "Please first enter your git email to proceed:" GIT_EMAIL
    done
    
    git config --global user.email "$GIT_EMAIL"
fi

if [ -z $GIT_USER_NAME ]
then
    read -p "Enter a git username: " GIT_USER_NAME

    while [[ -z $GIT_USER_NAME ]]; do
    read -p "Please first enter your git username to proceed:" GIT_USER_NAME
    done

    git config --global user.name "$GIT_USER_NAME"
fi

if [ -z $GIT_PASSWORD ]
then
    read -p "Enter your git password: " GIT_PASSWORD

    while [[ -z $GIT_PASSWORD ]]; do
    read -p "Please first enter your git password to proceed:" GIT_PASSWORD
    done

    git config --global user.password "$GIT_PASSWORD"
fi

echo "Adding git changes..."

git add .

read -p "Enter a commit message. This is a short description of what your changes to this project are about:" COMMIT_MESSAGE

while [[ -z $COMMIT_MESSAGE ]]; do
    read -p "Please first enter the commit message to proceed:" COMMIT_MESSAGE
done

git commit -m "$COMMIT_MESSAGE"

echo "Pushing commited changes to remote repository..."

git push origin main

cd ./moving-to-mastery-client

echo "Installing dependencies..."

npm install

echo "Deploying the client App..."

npm run deploy

