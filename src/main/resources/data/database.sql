DROP TABLE IF EXISTS PRODUCT;
CREATE TABLE PRODUCT (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  product_name VARCHAR(250) NOT NULL,
  color_code VARCHAR(250) NOT NULL,
  product_size VARCHAR(250) DEFAULT NULL
);

DROP TABLE IF EXISTS CONTENT;
CREATE TABLE CONTENT (
  id INT AUTO_INCREMENT PRIMARY KEY,
  locale VARCHAR(5) NOT NULL,
  description VARCHAR(250) NOT NULL,
  product_id INTEGER DEFAULT NULL,
  CONSTRAINT FK_PRODUCT_ID_FOR_CONTENT FOREIGN KEY (product_id) REFERENCES PRODUCT(id)
);

DROP TABLE IF EXISTS COLLECTION;
CREATE TABLE COLLECTION (
  id INT AUTO_INCREMENT,
  collection_name VARCHAR(250) NOT NULL,
  locale VARCHAR(5) NOT NULL,
  PRIMARY KEY (id, locale)
);

DROP TABLE IF EXISTS COLLECTION_PRODUCT;
CREATE TABLE COLLECTION_PRODUCT (
  collection INT NOT NULL,
  product_id INT NOT NULL,
  PRIMARY KEY (collection, product_id),
  CONSTRAINT FK_PRODUCT_ID FOREIGN KEY (product_id) REFERENCES PRODUCT(id)
);

