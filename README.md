# mall-server

E-commerce ssm framework: spring+springMVC+Mybatis
电商后台ssm框架：spring+springMVC+Mybatis

There are mainly 5 modules: user module, commodity module, shopping cart module, address module, payment module
主要有5个模块：用户模块、商品模块、购物车模块、地址模块、支付模块

## User module User Module

    +Presentation layer: controller under the UserController
    +Business layer: UserServiceImpl under the impl package under the service package
    +Data provided: UserMapper under dao package
    +SQL statement: under the resources package under the mappers package UserMapper.xml
### Main interface Main interface
*
   Login interface
*
    User exit interface
*
   Registered interface
*
    Check interface
*
   Get user information interface when logging in
*
    What is the problem with the query forgot password?
*
   Verify the password recovery problem
*
    Password problem reset password interface
*
   Reset password interface in login state
*
    Update user information interface
*
   Get user information interface
## Product Module
    +Presentation layer: ControllerController under ProductController, ProductManageController
    +Business layer: ProductServiceImpl, CategoryServiceImpl under the impl package under the service package
    +Data provided: dao package under ProductMapper, CategoryMapper
    +SQL statement: under the resources package under the mappers package under the dao package ProductMapper.xml, CategoryMapper.xml
### Main interface at the front desk
*
   Product details interface
*
    Product list interface
### Background management main interface
*
   Save product interface
*
    Modify the merchandise sales status interface
*
   Get the product details interface
*
    Get the list interface of the item
*
   Query product interface
*
    Upload product interface
*
   Upload product image interface
*
   Increase product classification interface
*
    Modify the name interface of the item
*
   Get the name interface of the item
*
    Get the list interface of the item
*
   Query product interface
*
    Upload product interface
*
   Upload product image interface
## Shopping Cart Module
    +Presentation layer: Controller under the CartController
    +Business layer: CartServiceImpl under the impl package under the service package
    +Data provided: CAT package under CartMapper
    +SQL statement: under the resources package under the mappers package CartMapper.xml
### Main interface
*
   Shopping cart's merchandise interface
*
    Add shopping cart item interface
*
   Update shopping cart interface
*
    Delete shopping cart item interface
*
   Product selection, reverse selection interface
*
    Query the number of shopping carts
## Address Module
    +Presentation layer: controller under the ShippingController
    +Business layer: ShippingServiceImpl under the impl package under the service package
    +Data provided: dao package under ShippingMapper
    +SQL statement: under the resources package under the mappers package ShippingMapper.xml
### Main interface
*
   Add address interface
*
    Delete address interface
*
   Update address interface
*
    Address detail interface
*
   Address list interface
## Payment module
  Not yet updated to github mainly face to face payment


### 用户模块

    表现层：controller包下UserController
    业务层：service包下impl包下的UserServiceImpl
    数据提供:dao包下UserMapper
    SQL语句：resources包下mappers包下UserMapper.xml
### 主要接口
*   
   登录接口
*
    用户退出接口
*   
   注册的接口
*
    校验接口
*   
   登录情况下获取用户信息接口
*
    查询忘记密码的问题是什么接口
*   
   校验找回密码问题
*
    密码问题重置密码接口
*   
   登录状态下重置密码接口
*
    更新用户信息接口
*   
   获取用户信息接口
## 商品模块
    表现层：controller包下ProductController、ProductManageController
    业务层：service包下impl包下的ProductServiceImpl、CategoryServiceImpl
    数据提供:dao包下ProductMapper、CategoryMapper
    SQL语句：resources包下mappers包下dao包下ProductMapper.xml、CategoryMapper.xml
### 前台主要接口
*   
   商品详情接口
*
    商品列表接口
### 后台管理主要接口
*   
   保存商品接口
*
    修改商品销售状态接口
*   
   获取商品详情接口
*
    获取商品的列表接口
*   
   查询商品接口
*
    上传商品接口  
*   
   上传商品图片接口
*   
   增加商品分类接口
*
    修改商品的名称接口
*   
   获取商品的名称接口
*
    获取商品的列表接口
*   
   查询商品接口
*
    上传商品接口  
*   
   上传商品图片接口
## 购物车模块
    表现层：controller包下CartController
    业务层：service包下impl包下的CartServiceImpl
    数据提供:dao包下CartMapper
    SQL语句：resources包下mappers包下CartMapper.xml
### 主要接口
*   
   购物车的所以商品接口
*
    添加购物车商品接口
*   
   更新购物车接口
*
    删除购物车商品接口
*   
   商品全选、反选接口
*
    查询购物车数量接口
## 地址模块
    表现层：controller包下ShippingController
    业务层：service包下impl包下的ShippingServiceImpl
    数据提供:dao包下ShippingMapper
    SQL语句：resources包下mappers包下ShippingMapper.xml
### 主要接口
*   
   增加地址接口
*
    删除地址接口
*   
   更新地址接口
*
    地址详情接口
*   
   地址列表接口
## 支付模块
  尚未更新到github主要是面对面支付






