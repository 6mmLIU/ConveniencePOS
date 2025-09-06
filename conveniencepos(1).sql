/*
 Navicat Premium Data Transfer

 Source Server         : localhost8.0.19
 Source Server Type    : MySQL
 Source Server Version : 80019
 Source Host           : localhost:3307
 Source Schema         : conveniencepos

 Target Server Type    : MySQL
 Target Server Version : 80019
 File Encoding         : 65001

 Date: 26/04/2025 14:26:35
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for members
-- ----------------------------
DROP TABLE IF EXISTS `members`;
CREATE TABLE `members`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `phone_number` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `points` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `UKleyi5nsi9s2i9ufmurv5o5ehu`(`name` ASC) USING BTREE,
  UNIQUE INDEX `UK99xbxdwmyun0ehfiwpbntlqs5`(`phone_number` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 32 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of members
-- ----------------------------
INSERT INTO `members` VALUES (3, 'Marie Chavez', '614-579-3284', 829);
INSERT INTO `members` VALUES (4, 'Eva Alvarez', '90-9757-6572', 970);
INSERT INTO `members` VALUES (5, 'Bernard Sanders', '312-975-3019', 132);
INSERT INTO `members` VALUES (6, 'Larry Diaz', '760-6361-2916', 820);
INSERT INTO `members` VALUES (7, 'Li Lan', '838-876-1563', 477);
INSERT INTO `members` VALUES (8, 'Kwok Chiu Wai', '90-3260-0428', 827);
INSERT INTO `members` VALUES (9, 'Hayashi Rin', '74-150-2293', 293);
INSERT INTO `members` VALUES (10, 'Edwin Alexander', '139-4903-8163', 813);
INSERT INTO `members` VALUES (11, 'Tam Ka Keung', '5854 267407', 137);
INSERT INTO `members` VALUES (12, 'Matsui Kaito', '718-908-4528', 697);
INSERT INTO `members` VALUES (13, 'Jin Shihan', '80-4560-4907', 706);
INSERT INTO `members` VALUES (14, 'Raymond Ramirez', '838-074-1410', 547);
INSERT INTO `members` VALUES (15, 'Chan Wing Sze', '3-0447-5465', 880);
INSERT INTO `members` VALUES (16, 'Ma Fat', '330-066-9075', 283);
INSERT INTO `members` VALUES (17, 'Xia Yunxi', '80-1054-7971', 755);
INSERT INTO `members` VALUES (18, 'Deborah Jackson', '614-582-3645', 623);
INSERT INTO `members` VALUES (19, 'Christopher Kim', '(1223) 59 7919', 644);
INSERT INTO `members` VALUES (21, '6mm', '11111111', 888);
INSERT INTO `members` VALUES (31, '444', '123456', 666);

-- ----------------------------
-- Table structure for payments
-- ----------------------------
DROP TABLE IF EXISTS `payments`;
CREATE TABLE `payments`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `amount` decimal(12, 2) NOT NULL,
  `method` enum('BANK_CARD','CASH','MOBILE_PAY') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `payment_time` datetime(6) NOT NULL,
  `sale_id` bigint NOT NULL,
  `quantity` int NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 236 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of payments
-- ----------------------------
INSERT INTO `payments` VALUES (276, 15.00, 'CASH', '2025-04-26 06:13:32.602620', 1003, 1);
INSERT INTO `payments` VALUES (277, 10.00, 'CASH', '2025-04-26 06:13:57.338936', 1006, 1);
INSERT INTO `payments` VALUES (278, 15.00, 'MOBILE_PAY', '2025-04-26 06:16:38.066955', 1017, 1);
INSERT INTO `payments` VALUES (279, 10.00, 'MOBILE_PAY', '2025-04-26 06:16:38.117487', 1017, 1);
INSERT INTO `payments` VALUES (280, 25.00, 'MOBILE_PAY', '2025-04-26 06:16:38.121487', 1017, 1);
INSERT INTO `payments` VALUES (281, 22.00, 'MOBILE_PAY', '2025-04-26 06:16:38.125488', 1017, 1);
INSERT INTO `payments` VALUES (282, 15.00, 'BANK_CARD', '2025-04-26 06:17:42.460422', 1018, 1);
INSERT INTO `payments` VALUES (283, 10.00, 'BANK_CARD', '2025-04-26 06:17:42.465475', 1018, 1);
INSERT INTO `payments` VALUES (284, 25.00, 'BANK_CARD', '2025-04-26 06:17:42.471443', 1018, 1);
INSERT INTO `payments` VALUES (285, 18.00, 'BANK_CARD', '2025-04-26 06:17:42.475388', 1018, 1);
INSERT INTO `payments` VALUES (286, 8.00, 'BANK_CARD', '2025-04-26 06:17:42.479673', 1018, 1);
INSERT INTO `payments` VALUES (287, 12.00, 'BANK_CARD', '2025-04-26 06:17:42.482674', 1018, 1);
INSERT INTO `payments` VALUES (288, 20.00, 'BANK_CARD', '2025-04-26 06:17:42.485674', 1018, 1);
INSERT INTO `payments` VALUES (289, 10.00, 'BANK_CARD', '2025-04-26 06:17:42.488672', 1018, 1);
INSERT INTO `payments` VALUES (290, 20.00, 'BANK_CARD', '2025-04-26 06:17:42.492342', 1018, 1);
INSERT INTO `payments` VALUES (291, 555.00, 'BANK_CARD', '2025-04-26 06:17:42.495340', 1018, 1);
INSERT INTO `payments` VALUES (292, 22.00, 'BANK_CARD', '2025-04-26 06:17:42.498340', 1018, 1);
INSERT INTO `payments` VALUES (293, 10.00, 'CASH', '2025-04-26 06:21:42.549156', 1001, 1);

-- ----------------------------
-- Table structure for products
-- ----------------------------
DROP TABLE IF EXISTS `products`;
CREATE TABLE `products`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `category` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `price` decimal(38, 2) NOT NULL,
  `stock` int NOT NULL,
  `safe_stock` int NOT NULL,
  `barcode` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `UKo61fmio5yukmmiqgnxf8pnavn`(`name` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 28 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of products
-- ----------------------------
INSERT INTO `products` VALUES (1, 'Fruit', 'Apple', 15.00, 89, 10, '');
INSERT INTO `products` VALUES (2, 'Fruit', 'Banana', 10.00, 15, 20, '');
INSERT INTO `products` VALUES (3, 'Vegetables', 'Carrot', 25.00, 156, 15, '');
INSERT INTO `products` VALUES (4, 'Vegetables', 'Tomato', 18.00, 128, 12, '');
INSERT INTO `products` VALUES (5, 'Drinks', 'Coke', 8.00, 15, 30, '');
INSERT INTO `products` VALUES (6, 'Drinks', 'Orange Juice', 12.00, 19, 25, '');
INSERT INTO `products` VALUES (7, 'Snacks', 'Chips', 20.00, 9, 10, '');
INSERT INTO `products` VALUES (24, '食品', '测试商品1', 10.00, 10, 10, '');
INSERT INTO `products` VALUES (25, '日用品', '测试商品2', 20.00, 10, 8, '');
INSERT INTO `products` VALUES (26, 'Fruit', 'haha', 555.00, 535, 55, '3363214');
INSERT INTO `products` VALUES (27, 'Fruit', '444', 22.00, 21, 2222, '22222');

-- ----------------------------
-- Table structure for purchases
-- ----------------------------
DROP TABLE IF EXISTS `purchases`;
CREATE TABLE `purchases`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `product_id` bigint NOT NULL,
  `purchase_time` datetime(6) NOT NULL,
  `quantity` int NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of purchases
-- ----------------------------
INSERT INTO `purchases` VALUES (1, 1, '2025-04-25 19:43:34.000000', 50);
INSERT INTO `purchases` VALUES (2, 2, '2025-04-25 19:43:34.000000', 30);
INSERT INTO `purchases` VALUES (3, 1, '2025-04-25 11:54:06.340222', 10);
INSERT INTO `purchases` VALUES (4, 1, '2025-04-25 11:54:07.697083', 10);
INSERT INTO `purchases` VALUES (5, 2, '2025-04-25 11:54:09.661824', 10);
INSERT INTO `purchases` VALUES (6, 1, '2025-04-25 11:54:13.251213', 10);

-- ----------------------------
-- Table structure for roles
-- ----------------------------
DROP TABLE IF EXISTS `roles`;
CREATE TABLE `roles`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `UKofx66keruapi6vyqpv6f2or37`(`name` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of roles
-- ----------------------------
INSERT INTO `roles` VALUES (1, 'ROLE_ADMIN');
INSERT INTO `roles` VALUES (2, 'ROLE_USER');

-- ----------------------------
-- Table structure for sales
-- ----------------------------
DROP TABLE IF EXISTS `sales`;
CREATE TABLE `sales`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `product_id` bigint NOT NULL,
  `quantity` int NOT NULL,
  `sale_time` datetime(6) NOT NULL,
  `total_price` double NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 24 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sales
-- ----------------------------
INSERT INTO `sales` VALUES (4, 1, 3, '2023-12-17 10:00:00.000000', 10000);
INSERT INTO `sales` VALUES (5, 2, 5, '2024-12-17 11:00:00.000000', 150);
INSERT INTO `sales` VALUES (6, 3, 2, '2024-12-17 12:00:00.000000', 320);
INSERT INTO `sales` VALUES (7, 4, 10, '2024-12-17 13:00:00.000000', 100);
INSERT INTO `sales` VALUES (8, 1, 4, '2024-12-17 14:00:00.000000', 60);
INSERT INTO `sales` VALUES (9, 2, 6, '2024-12-17 15:00:00.000000', 180);
INSERT INTO `sales` VALUES (10, 3, 3, '2024-12-17 16:00:00.000000', 480);
INSERT INTO `sales` VALUES (11, 4, 7, '2024-12-17 17:00:00.000000', 175);
INSERT INTO `sales` VALUES (12, 1, 8, '2024-12-17 18:00:00.000000', 120);
INSERT INTO `sales` VALUES (13, 2, 5, '2024-12-17 19:00:00.000000', 250);
INSERT INTO `sales` VALUES (14, 3, 4, '2024-12-17 20:00:00.000000', 400);
INSERT INTO `sales` VALUES (15, 4, 6, '2024-12-17 21:00:00.000000', 300);
INSERT INTO `sales` VALUES (16, 1, 9, '2024-12-18 10:00:00.000000', 135);
INSERT INTO `sales` VALUES (17, 2, 8, '2024-12-18 11:00:00.000000', 320);
INSERT INTO `sales` VALUES (18, 3, 5, '2024-12-18 12:00:00.000000', 500);
INSERT INTO `sales` VALUES (19, 4, 4, '2024-12-18 13:00:00.000000', 220);
INSERT INTO `sales` VALUES (20, 1, 3, '2024-12-18 14:00:00.000000', 90);
INSERT INTO `sales` VALUES (21, 2, 7, '2024-12-18 15:00:00.000000', 270);
INSERT INTO `sales` VALUES (22, 3, 6, '2024-12-18 16:00:00.000000', 600);
INSERT INTO `sales` VALUES (23, 4, 5, '2024-12-18 17:00:00.000000', 400);

-- ----------------------------
-- Table structure for user_roles
-- ----------------------------
DROP TABLE IF EXISTS `user_roles`;
CREATE TABLE `user_roles`  (
  `user_id` bigint NOT NULL,
  `role` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  INDEX `FKhfh9dx7w3ubf1co1vdev94g3f`(`user_id` ASC) USING BTREE,
  CONSTRAINT `FKhfh9dx7w3ubf1co1vdev94g3f` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_roles
-- ----------------------------
INSERT INTO `user_roles` VALUES (1, 'ROLE_ADMIN');
INSERT INTO `user_roles` VALUES (2, 'USER');
INSERT INTO `user_roles` VALUES (3, 'USER');
INSERT INTO `user_roles` VALUES (4, 'USER');
INSERT INTO `user_roles` VALUES (5, 'USER');
INSERT INTO `user_roles` VALUES (10, 'USER');
INSERT INTO `user_roles` VALUES (11, 'USER');
INSERT INTO `user_roles` VALUES (12, 'USER');

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `UK6dotkott2kjsp8vw4d0m25fb7`(`email` ASC) USING BTREE,
  UNIQUE INDEX `UKr43af9ap4edm43mmtq01oddj6`(`username` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES (1, '6mm123456@duk.com', '$2a$12$6o0xiXyxeu1FZYVK/fh3persqgX55GdsFW9UcafDUQGktTT045U7q', 'admin');
INSERT INTO `users` VALUES (2, '6mm123456@duck.com', '$2a$10$..XyTMSBZfd8PEfq.Ll5hO1/rwrsZaxci0OgKyx06h9FkGBmDb5B2', 'user');
INSERT INTO `users` VALUES (3, '6mm@hhh.com', '$2a$10$i3.B7spNjux3/lTRA9S9RuD4DkFnKxZgdU6GVe0rqOhoWV7xhTCUq', 'test');
INSERT INTO `users` VALUES (4, '666666@666.com', '$2a$10$F3XyT0f9o0OSi9c2SyEwm.nS8s.aVGTDIBA0Bq5nlzGxyJu8qKAjm', 'thanks');
INSERT INTO `users` VALUES (5, 'helloworld@nihao.com', '$2a$10$RdYK91qRiFvdeeyPfTFogek7cB0iQpVwaRwIqG9hR/RnHa9Yl3uOC', 'nihao');
INSERT INTO `users` VALUES (10, '123456@dck.com', '$2a$10$lUlY0D5lUh8xF/p.FdO9euc1p0VR5D4gsa98SVclOxDpOM1qfLoae', 'test5');
INSERT INTO `users` VALUES (11, '6mm12346@duck.com', '$2a$10$wmLz6fm0woRkfeIC1eHk0.0asqhAdakZQMxj8vwLebJYkXPxaMCvO', 'nihao5');
INSERT INTO `users` VALUES (12, '1236@qq.com', '$2a$10$0Zpe/BpbZ0iMBaN88XD1MeVu91jKItKsIR5OvWHviKYVuCvymHMGy', 'nihao6');

SET FOREIGN_KEY_CHECKS = 1;
