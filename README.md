# ConveniencePOS - 便利店销售系统

## 项目简介

ConveniencePOS 是一个基于 Spring Boot 的便利店销售系统，旨在提供商品管理、库存管理、销售管理、会员管理等多种功能，以满足便利店日常运营需求。

## 功能模块

### 1. 商品管理

- 添加、修改、删除和查询商品。
- 支持商品分类管理。

### 2. 库存管理

- 实时监控库存状态。
- 自动发送库存不足的提醒邮件。
- 设置安全库存，避免缺货或库存过剩。

### 3. 销售管理

- 记录每笔销售数据。
- 按时间统计销售额，并生成销售报表。

### 4. 会员管理

- 录入、修改和查询会员信息。
- 提供会员积分和优惠券功能。

### 5. 收银结算

- 支持多种支付方式（现金、银行卡、移动支付等）。

### 6. 数据统计与分析

- 对销售、库存和会员数据进行分析。
- 为经营决策提供数据支持。

### 7. 权限管理

- 根据员工角色分配不同权限。
- 确保系统数据安全。

### 8. 系统设置

- 门店、员工等基本信息管理。
- 系统参数设置。

## 技术栈

- **后端**: Spring Boot、Spring Security、Spring Data JPA
- **前端**: Thymeleaf、HTML、CSS
- **数据库**: MySQL
- **任务调度**: Spring Scheduling
- **邮件服务**: Spring Mail
- **构建工具**: Maven

## 安装与运行

### 1. 克隆项目

```bash
git clone https://github.com/6mmLIU/ConveniencePOS.git
cd ConveniencePOS
```

### 2. 配置数据库

- 在 `src/main/resources/application.properties` 中设置 MySQL 数据库连接信息：

```properties
spring.datasource.url=jdbc:mysql://localhost:3307/conveniencepos?useSSL=false&serverTimezone=UTC
spring.datasource.username=your-username
spring.datasource.password=your-password
```

### 3. 构建与运行

- 使用 Maven 构建项目：

```bash
mvn clean install
```

- 运行项目：

```bash
mvn spring-boot:run
```

### 4. 访问应用

- 打开浏览器，访问 [http://localhost:8080](http://localhost:8080)。

## 文件结构

```plaintext
# 项目目录结构
src
└── main
    ├── java
    │   └── com.example.conveniencepos
    │       ├── config
    │       │   └── SecurityConfig
    │       ├── controller
    │       │   ├── CustomerProductController
    │       │   ├── CustomErrorController
    │       │   ├── LoginController
    │       │   ├── MemberController
    │       │   ├── PaymentController
    │       │   ├── ProductController
    │       │   ├── RegisterController
    │       │   ├── SaleController
    │       │   └── UserController
    │       ├── entity
    │       │   ├── Member
    │       │   ├── Payment
    │       │   ├── Product
    │       │   ├── Sale
    │       │   └── User
    │       ├── repository
    │       │   ├── MemberRepository
    │       │   ├── PaymentRepository
    │       │   ├── ProductRepository
    │       │   ├── SaleRepository
    │       │   └── UserRepository
    │       ├── scheduler
    │       │   └── StockAlertScheduler
    │       ├── service
    │       │   ├── MemberService
    │       │   ├── NotificationService
    │       │   ├── PaymentService
    │       │   ├── ProductService
    │       │   ├── SaleService
    │       │   ├── UserDetailsServiceImpl
    │       │   └── UserService
    │       ├── util
    │       │   └── CustomAuthenticationSuccessHandler
    │       └── ConveniencePosApplication
    └── resources

```

## 示例

### 登录页面
<img width="1270" alt="cd5b4bb424f4ec1d50cda4dff43fa33" src="https://github.com/user-attachments/assets/4b316b3f-68a2-45d7-bd8e-4d38ef3762ad" />



### 销售统计页面
<img width="1218" alt="9603f4b173d89206b7e544a61dbf93a" src="https://github.com/user-attachments/assets/61f7602c-72b6-43c1-8f56-ad77b0072e9b" />



## 贡献

欢迎提交 Issues 或 Pull Requests！

## 许可证

本项目采用 [Apache 2.0 许可证](LICENSE) 开源。

