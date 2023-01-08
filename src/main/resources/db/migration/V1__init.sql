CREATE TABLE users (
  id BIGSERIAL PRIMARY KEY,
  username VARCHAR(100) NOT NULL,
  email VARCHAR(254) NOT NULL,
  password VARCHAR(200) NOT NULL,
  disabled CHAR(1) DEFAULT 'N',
  last_invalid_password_ts TIMESTAMP WITH TIME ZONE,
  invalid_passwords SMALLINT,
  locked CHAR(1) DEFAULT 'N',
  created_ts TIMESTAMP WITH TIME ZONE NOT NULL,
  updated_ts TIMESTAMP WITH TIME ZONE
);
ALTER TABLE users ADD CONSTRAINT unq_users_username UNIQUE(username);
ALTER TABLE users ADD CONSTRAINT chk_users_disabled CHECK (disabled = 'Y' or disabled = 'N');
ALTER TABLE users ADD CONSTRAINT chk_users_locked CHECK (locked = 'Y' or locked = 'N');


CREATE TABLE static_pages (
  id BIGSERIAL PRIMARY KEY,
  title VARCHAR(100) NOT NULL,
  alias VARCHAR(100) NOT NULL,
  content TEXT NOT NULL,
  published CHAR(1) DEFAULT 'N',
  published_at TIMESTAMP WITH TIME ZONE,
  created_ts TIMESTAMP WITH TIME ZONE NOT NULL,
  updated_ts TIMESTAMP WITH TIME ZONE,
  created_by BIGINT NOT NULL,
  updated_by BIGINT
);
ALTER TABLE static_pages ADD CONSTRAINT unq_static_pages_title UNIQUE (title);
ALTER TABLE static_pages ADD CONSTRAINT unq_static_pages_alias UNIQUE (alias);
ALTER TABLE static_pages ADD CONSTRAINT chk_static_pages_published CHECK (published = 'Y' or published = 'N');
ALTER TABLE static_pages ADD CONSTRAINT fk_static_pages_users_created_by
  FOREIGN KEY(created_by) REFERENCES users(id);
ALTER TABLE static_pages ADD CONSTRAINT fk_static_pages_users_updated_by
  FOREIGN KEY(updated_by) REFERENCES users(id);


CREATE TABLE menu_items (
  id BIGSERIAL PRIMARY KEY,
  name VARCHAR(20),
  url VARCHAR(100),
  active CHAR(1) DEFAULT 'N',
  priority INT DEFAULT 0,
  created_ts TIMESTAMP WITH TIME ZONE NOT NULL,
  updated_ts TIMESTAMP WITH TIME ZONE,
  created_by BIGINT NOT NULL,
  updated_by BIGINT
);
ALTER TABLE menu_items ADD CONSTRAINT unq_menu_items_name UNIQUE(name);
ALTER TABLE menu_items ADD CONSTRAINT fk_menu_items_users_created_by
  FOREIGN KEY(created_by) REFERENCES users(id);
ALTER TABLE menu_items ADD CONSTRAINT fk_menu_items_users_updated_by
  FOREIGN KEY(updated_by) REFERENCES users(id);


CREATE TABLE categories (
  id BIGSERIAL PRIMARY KEY,
  name VARCHAR(20) NOT NULL,
  alias VARCHAR(20) NOT NULL,
  created_ts TIMESTAMP WITH TIME ZONE NOT NULL,
  updated_ts TIMESTAMP WITH TIME ZONE,
  created_by BIGINT NOT NULL,
  updated_by BIGINT
);
ALTER TABLE categories ADD CONSTRAINT unq_categories_name UNIQUE(name);
ALTER TABLE categories ADD CONSTRAINT unq_categories_alias UNIQUE(alias);
ALTER TABLE categories ADD CONSTRAINT fk_categories_users_created_by
  FOREIGN KEY(created_by) REFERENCES users(id);
ALTER TABLE categories ADD CONSTRAINT fk_categories_users_updated_by
  FOREIGN KEY(updated_by) REFERENCES users(id);


CREATE TABLE articles (
  id BIGSERIAL PRIMARY KEY,
  title VARCHAR(100) NOT NULL,
  alias VARCHAR(100) NOT NULL,
  published CHAR(1),
  published_at TIMESTAMP WITH TIME ZONE,
  created_ts TIMESTAMP WITH TIME ZONE NOT NULL,
  category_id BIGINT,
  updated_ts TIMESTAMP WITH TIME ZONE,
  created_by BIGINT NOT NULL,
  updated_by BIGINT
);
ALTER TABLE articles ADD CONSTRAINT unq_articles_title UNIQUE(title);
ALTER TABLE articles ADD CONSTRAINT unq_articles_alias UNIQUE(alias);
ALTER TABLE articles ADD CONSTRAINT chk_articles_published
  CHECK(published IS NULL OR published = 'Y' or published = 'N');
ALTER TABLE articles ADD CONSTRAINT fk_articles_categories_category_id
  FOREIGN KEY(category_id) REFERENCES categories(id);
ALTER TABLE articles ADD CONSTRAINT fk_articles_users_created_by
  FOREIGN KEY(created_by) REFERENCES users(id);
ALTER TABLE articles ADD CONSTRAINT fk_articles_users_updated_by
  FOREIGN KEY(updated_by) REFERENCES users(id);
