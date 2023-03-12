

echo "Adding git changes..."

git add .

@echo off
set /p message=Enter a commit message. This is a short description of what your changes to this project are about:

git commit -m "%message%"

echo Pushing commited changes to remote repository...

git push

cd ./moving-to-mastery-client

echo "Installing dependencies..."

npm install

echo "Deploying the client App..."

npm run deploy
