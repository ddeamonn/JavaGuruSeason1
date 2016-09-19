select Products.Name, Sales.Price, Sales.Quantity, Sales.SaleSum
--select *
from Products, Sales
where Products.Id = Sales.ProductID

SELECT SaleID as SID, SUM(SaleSum) as Sum FROM SALES GROUP BY SaleID;

SELECT datetime(1346142933585/1000, 'unixepoch');

SELECT datetime(1474312314736/1000, 'unixepoch');

select datetime(1474312314736/1000,'unixepoch','localtime') from SALES

