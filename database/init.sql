-- Reconstructed demo schema matching the DAO fields, not an export of personal data.
CREATE DATABASE IF NOT EXISTS mt CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE mt;

CREATE TABLE IF NOT EXISTS `user` (
    username VARCHAR(32) NOT NULL PRIMARY KEY,
    password VARCHAR(128) NOT NULL,
    tel VARCHAR(20) NOT NULL
);

CREATE TABLE IF NOT EXISTS milktea (
    mid VARCHAR(16) NOT NULL PRIMARY KEY,
    mname VARCHAR(64) NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    image VARCHAR(128) NOT NULL
);

-- These are example products using the images bundled with this project.
INSERT IGNORE INTO milktea (mid, mname, price, image) VALUES
('m001', '珍珠奶茶', 12.00, 'zz.png'),
('m002', '椰果奶茶', 13.00, 'yg.png'),
('m003', '红豆奶茶', 14.00, 'hd.png');
