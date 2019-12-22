CREATE TABLE hits
(
  id serial PRIMARY KEY,
  user_id int NOT NULL,
  page text NOT NULL,
  hit_date timestamp NOT NULL DEFAULT NOW()
);

