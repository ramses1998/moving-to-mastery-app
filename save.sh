#/bin/bash
echo "Adding git changes..."
git add .

read -p "Enter a commit message. This is a short description of what your changes to this project are about:" COMMIT_MESSAGE


while [[ -z $COMMIT_MESSAGE ]]; do
    read -p "Please first enter the commit message to proceed:" COMMIT_MESSAGE
done

git commit -m "$COMMIT_MESSAGE"

echo "Pushing commited changes to remote repository..."

git push

cd ./moving-to-mastery-client

echo "Deploying the client App..."

npm run deploy
