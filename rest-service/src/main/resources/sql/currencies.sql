INSERT INTO common.currency(id, code, name) VALUES(
uuid_in(md5(random()::text || now()::text)::cstring), 'KZT', 'Казахстанский тенге');

INSERT INTO common.currency(id, code, name) VALUES(
uuid_in(md5(random()::text || now()::text)::cstring), 'USD', 'Американский доллар');