CREATE TABLE static_pages (
 id BIGINT PRIMARY KEY,
 title VARCHAR(100) NOT NULL,
 alias VARCHAR(100) NOT NULL,
 content TEXT NOT NULL,
 published CHAR(1) DEFAULT 'N',
 published_at TIMESTAMP WITH TIME ZONE
);
ALTER TABLE static_pages ADD CONSTRAINT unq_static_pages_title UNIQUE (title);
ALTER TABLE static_pages ADD CONSTRAINT unq_static_pages_alias UNIQUE (alias);
ALTER TABLE static_pages ADD CONSTRAINT chk_static_pages_published CHECK (published = 'Y' or published = 'N');


CREATE TABLE menu_items (
  id BIGINT PRIMARY KEY,
  name VARCHAR(20),
  url VARCHAR(100),
  active CHAR(1) DEFAULT 'N',
  priority INT DEFAULT 0
);
ALTER TABLE menu_items ADD CONSTRAINT unq_menu_items_name UNIQUE(name);


CREATE TABLE categories (
  id BIGINT PRIMARY KEY,
  name VARCHAR(20) NOT NULL,
  alias VARCHAR(20) NOT NULL
);
ALTER TABLE categories ADD CONSTRAINT unq_categories_name UNIQUE(name);
ALTER TABLE categories ADD CONSTRAINT unq_categories_alias UNIQUE(alias);


CREATE TABLE articles (
  id BIGINT PRIMARY KEY,
  title VARCHAR(100) NOT NULL,
  alias VARCHAR(100) NOT NULL,
  published CHAR(1),
  published_at TIMESTAMP WITH TIME ZONE
);
ALTER TABLE articles ADD CONSTRAINT unq_articles_title UNIQUE(title);
ALTER TABLE articles ADD CONSTRAINT unq_articles_alias UNIQUE(alias);
ALTER TABLE articles ADD CONSTRAINT chk_articles_published
  CHECK(published IS NULL OR published = 'Y' or published = 'N');

