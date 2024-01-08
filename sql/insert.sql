INSERT INTO `news-website`.`role` (`code`, `name`)
VALUES ('admin', 'Quản trị viên');
INSERT INTO `news-website`.`role` (`code`, `name`)
VALUES ('author', 'Tác giả');
INSERT INTO `news-website`.`role` (`code`, `name`)
VALUES ('user', 'Người dùng');

INSERT INTO `` (`id`, `created_by`, `created_date`, `modified_by`, `modified_date`, `published_date`, `activated`,
                `avatar_url`, `email`, `first_name`, `last_name`, `password`, `token`, `username`)
VALUES (1, NULL, NULL, NULL, NULL, NULL, 1, NULL, 'admin@admin.com', 'Nguyễn', 'Văn Toàn',
        '$2a$10$lpOtE5yW9YBCENoqCMk7TefPxlo0pqLy8q/Tbfg0/Apf3/ZDI7V6O', NULL, 'admin');
INSERT INTO `` (`id`, `created_by`, `created_date`, `modified_by`, `modified_date`, `published_date`, `activated`,
                `avatar_url`, `email`, `first_name`, `last_name`, `password`, `token`, `username`)
VALUES (2, NULL, NULL, NULL, NULL, NULL, 1, NULL, 'author01@author.com', 'Nguyễn', 'Văn Toàn',
        '$2a$10$mhuc8RS45i7XbNX0yHlOhebwOO437q7XoKY.AElMW05.5G60CGVGK', NULL, 'author01');
INSERT INTO `` (`id`, `created_by`, `created_date`, `modified_by`, `modified_date`, `published_date`, `activated`,
                `avatar_url`, `email`, `first_name`, `last_name`, `password`, `token`, `username`)
VALUES (3, NULL, NULL, NULL, NULL, NULL, 1, NULL, 'user@user.com', 'Nguyễn', 'Văn Toàn',
        '$2a$10$mhuc8RS45i7XbNX0yHlOhebwOO437q7XoKY.AElMW05.5G60CGVGK', NULL, 'user');


INSERT INTO `news-website`.`access` (`code`, `name`)
VALUES ('public', 'Công khai');
INSERT INTO `news-website`.`access` (`code`, `name`)
VALUES ('private', 'Riêng tư');
INSERT INTO `news-website`.`access` (`code`, `name`)
VALUES ('read-only', 'Chỉ đọc');

INSERT INTO `news-website`.`category` (`id`, `code`, `name`)
VALUES ('1', 'the-thao', 'Thể Thao');
INSERT INTO `news-website`.`category` (`code`, `name`, `parent_id`)
VALUES ('bong-da', 'Bóng Đá', '1');
INSERT INTO `news-website`.`category` (`code`, `name`, `parent_id`)
VALUES ('cac-mon-khac', 'Các Môn Khác', '1');
INSERT INTO `news-website`.`category` (`code`, `name`, `parent_id`)
VALUES ('trong-nuoc', 'Trong Nước', '1');

INSERT INTO `news-website`.`status` (`code`, `name`)
VALUES ('draft', 'Bản nháp');
INSERT INTO `news-website`.`status` (`code`, `name`)
VALUES ('pending', 'Đang chờ duyệt');
INSERT INTO `news-website`.`status` (`code`, `name`)
VALUES ('published', 'Đã đăng tải');
INSERT INTO `news-website`.`status` (`code`, `name`)
VALUES ('trash', 'Tạm xóa');


INSERT INTO user_role(user_id, role_id)
VALUES (1, 1);
INSERT INTO user_role(user_id, role_id)
VALUES (1, 3);
INSERT INTO user_role(user_id, role_id)
VALUES (2, 2);
INSERT INTO user_role(user_id, role_id)
VALUES (2, 3);
INSERT INTO user_role(user_id, role_id)
VALUES (3, 3);