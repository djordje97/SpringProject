                                                              INSERT INTO users(name,username,password) VALUES ('Petar Petrovic','pera','$2a$12$LtbYtEzOBdnsiD/E9Wtj2OEpK7kd3L7dHa5VoIzukKvjBWlReBAAW');
INSERT INTO users(name,username,password) VALUES ('Marko Markovic','marko','$2a$12$LtbYtEzOBdnsiD/E9Wtj2OEpK7kd3L7dHa5VoIzukKvjBWlReBAAW');

INSERT INTO authority(name) VALUES ('ROLE_USER');
INSERT INTO authority(name) VALUES ('ROLE_ADMIN');
INSERT INTO authority(name) VALUES ('ROLE_COMMENTATOR');

INSERT INTO user_authority(user_id,authority_id) VALUES (1,1);
INSERT INTO user_authority(user_id,authority_id) VALUES (2,2);

INSERT INTO posts(title,description,date,likes,dislikes,latitude,longitude,user_id)VALUES ('Avengers infinity war','New and best movie','2018-4-16',20,4,147.55,17.21,1);

INSERT INTO posts(title,description,date,likes,dislikes,latitude,longitude,user_id)VALUES ('Deadpool 2','New deadpool movie','2018-2-1',20,4,15.8,14.4,2);

INSERT INTO comments(title,description,date,likes,dislike,post_id,user_id)VALUES ('My comments','This is the best movie ever','2018-4-3',4,0,1,2);

INSERT INTO comments(title,description,date,likes,dislike,post_id,user_id)VALUES ('Best','This is awesome','2018-4-3',4,0,2,1);
INSERT INTO comments(title,description,date,likes,dislike,post_id,user_id)VALUES ('Best','This is awesome','2018-4-3',4,0,2,1);

INSERT INTO tags(name)VALUES ('best');
INSERT INTO tags(name)VALUES ('new');
INSERT INTO tags(name)VALUES ('2018');

INSERT INTO post_tags(post_id,tag_id)VALUES (1,2);
INSERT INTO post_tags(post_id,tag_id)VALUES (2,1);
INSERT INTO post_tags(post_id,tag_id)VALUES (1,3);