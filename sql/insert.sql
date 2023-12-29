INSERT INTO `newswebsitespring`.`role` (`code`, `name`)
VALUES ('admin', 'Quản trị viên');
INSERT INTO `newswebsitespring`.`role` (`code`, `name`)
VALUES ('user', 'Người dùng');
INSERT INTO `newswebsitespring`.`role` (`code`, `name`)
VALUES ('author', 'Tác giả');

INSERT INTO `newswebsitespring`.`user` (`fullname`, `password`, `username`)
VALUES ('Nguyễn Văn Toàn', '$2a$10$lpOtE5yW9YBCENoqCMk7TefPxlo0pqLy8q/Tbfg0/Apf3/ZDI7V6O', 'admin');
INSERT INTO `newswebsitespring`.`user` (`fullname`, `password`, `username`)
VALUES ('Nguyễn Văn Toàn', '$2a$10$NsvnmnCm7p/TrbkC.kGBseBtAsX3oYHGZxyzF9tdGLDb1yv0eTFKm', 'user');
