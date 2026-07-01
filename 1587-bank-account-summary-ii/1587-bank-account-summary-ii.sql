# Write your MySQL query statement below
select u.name AS NAME, sum(t.amount) as BALANCE
from Users u 
join Transactions t
on u.account=t.account
group by u.account
having BALANCE > 10000;