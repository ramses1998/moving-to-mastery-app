echo "Adding git changes..."

git add .

read -p "Enter a commit message. This is a short description of what your changes to this project are about:" COMMIT_MESSAGE

echo "Pushing commited changes to remote repository..."

git push origin main

cd ./moving-to-mastery-client

echo "Installing dependencies..."

npm install

echo "Deploying the client App..."

npm run deploy

