DROP TABLE IF EXISTS public.products;

CREATE TABLE public.products (
	product_id int4 NULL,
	account varchar NULL,
	balans numeric NULL,
	product_type varchar NULL,
	user_id int4 NULL
);

INSERT INTO public.products (product_id,account,balans,product_type,user_id) VALUES
	 (1,'4722',5000,'card',1),
	 (2,'4723',1000,'account',1),
	 (2,'47231',3000,'account',2),
	 (1,'47221',0,'card',2);