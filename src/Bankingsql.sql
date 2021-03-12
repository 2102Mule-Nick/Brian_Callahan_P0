CREATE TABLE users (
	user_id serial PRIMARY KEY,
	username VARCHAR ( 50 ) UNIQUE NOT NULL,
	pass_word VARCHAR ( 50 ) NOT NULL
);

CREATE TABLE accounts (
	user_id serial PRIMARY KEY,
	email VARCHAR ( 255 ),
	balance decimal,
	tier VARCHAR ( 255 )
);

create or replace function insert_into_account()
	returns trigger as $$
	begin
		if(TG_OP = 'UPDATE') then
		  UPDATE accounts
          SET tier = 'gold'
          WHERE balance >= 1000;
		end if;
	return new;
	end;
$$ language plpgsql;

create or replace function insert_into_account2()
	returns trigger as $$
	begin
		if(TG_OP = 'UPDATE') then
		  UPDATE accounts
          SET tier = 'bronze'
          WHERE balance <= 999;
		end if;
	return new;
	end;
$$ language plpgsql;

create trigger account_insert
	after update of balance on accounts
	for each row 
		execute procedure insert_into_account()
		
create trigger account_insert2
	after update of balance on accounts
	for each row 
		execute procedure insert_into_account2()
		
drop trigger account_insert on accounts;
		
create or replace PROCEDURE procedure_name()
LANGUAGE SQL
AS $$
UPDATE accounts SET tier='gold' WHERE balance > 1000;
$$;

CALL procedure_name();

select * from users;
select * from accounts;
truncate table users;
truncate table accounts;
drop table users;
drop table accounts;

