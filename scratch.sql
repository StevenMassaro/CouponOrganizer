
select c.id, c.store, c.deal, c.comment, c.expirationdate, c.file, c.extension
from coupons.coupons c
join coupons.file f
on c.id = f.id;

INSERT INTO coupons.coupons ("store", deal, "comment", expirationDate)
VALUES('fart', 'fart', 'fart', to_timestamp('2017-10-11', 'yyyy-mm-dd'))
RETURNING id;