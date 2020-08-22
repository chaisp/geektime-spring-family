**在学习极客时间SpringMVC全家桶中记录的小笔记**

- 使用entity.builder().属性().属性().build(),看起来比一个个set优雅多了

- 取出list中的ID,并每项之后加上逗号
```
    private String getJoinedOrderId(List<CoffeeOrder> list) {
            return list.stream().map(o -> o.getId().toString())
                    .collect(Collectors.joining(","));
        }
```
 
- 取出实体类中的list,然后对每个list中的数据在输出,双重循环的java8实现
 
- 使用JPA主要用于一些简单的CRUD,不用自己写sql语句,但是对于语句有告高阶要求时,还是MyBatis更好一些

- 使用Mybatis时,使用Mybatis-Generator可以根据数据库以及xml文件,生成相应的model和mapper文件
- @MapperScan注解在扫描配置的package后会找到Mapper接口并创建对应的Bean的
- 在model中不仅有实体类,还有相应的Example,通过example可以制定规则
```
    CoffeeExample example = new CoffeeExample();
    example.createCriteria().andNameEqualTo("latte");
    example.or(example.createCriteria().andNameIsNull());
    List<Coffee> list = coffeeMapper.selectByExample(example);
```
- 作为规范传入select语句中,感觉有点像regex,通过example的and可以无限增加条件,想要使用or语句,就要`example.or()`然后重新create一个规范就可以了

- 如果有对Mybatis中的mapper有手动修改的需要,那么最好生成两套model,自动生成的那套,会根据你的xml不断覆盖更新
- 而你手动写的就能避免被覆盖
