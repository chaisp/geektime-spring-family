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
```

```