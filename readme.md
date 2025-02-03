# Test

## User Endpoints (/users)

- Get all users

http GET http://localhost:8080/users

- Create a new user

http POST http://localhost:8080/users username="john_doe" email="john@example.com"
http POST http://localhost:8080/users username="mauro" email="mauro@gmail.com"
http POST http://localhost:8080/users username="mary" email="mary@hotmail.com"

- Get a user by username

http GET http://localhost:8080/users/username/mary

- Get a user by email

http GET http://localhost:8080/users/email/mauro@gmail.com

- Delete a user by ID

http DELETE http://localhost:8080/users/1

## Post Endpoints (/posts)

- Get all posts

http GET http://localhost:8080/posts

- Create a new post

http POST http://localhost:8080/posts title="My First Post" content="This is the content of the post." user:='{"id": 1}'

- Delete a post by ID

http DELETE http://localhost:8080/posts/1

## Comment Endpoints (/comments)

- Get all comments

http GET http://localhost:8080/comments

- Create a new comment

http POST http://localhost:8080/comments text="This is a comment" post:='{"id": 1}'

- Delete a comment by ID

http DELETE http://localhost:8080/comments/1

### Notes:

    Replace 1 with the actual ID of the entity when deleting or referencing an existing entity.
    Ensure that when creating a Post, the User ID exists.
    Ensure that when creating a Comment, the Post ID exists.
