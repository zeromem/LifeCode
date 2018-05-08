hive table t_order:
oid, uid, cid
1	1	c1
2	1	c1
3	2	c1
4	3	c1
5	1	c2
6	3	c3
7	1	c2
8	1	c3

订单号,用户id,城市id

#选出每个城市中订单量最大的用户对应的订单量(分组取最大)


select tmp.cid, tmp.uid, tmp.cnt
from (
  select cid, uid, count(*) as cnt,
  row_number() over (partition by cid order by count(*) desc) as rn
  from t_order
  group by cid, uid
) tmp
where tmp.rn <= 2;