-- Table: public."Client"

-- DROP TABLE public."Client";

CREATE TABLE public."Client"
(
  client_id integer NOT NULL DEFAULT nextval('"Client_client_id_seq"'::regclass),
  user_id integer,
  user_abn character varying(15),
  CONSTRAINT "Client_pkey" PRIMARY KEY (client_id),
  CONSTRAINT fk_user_id FOREIGN KEY (user_id)
      REFERENCES public.appuser (user_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public."Client"
  OWNER TO roster;


-- Table: public."Employee"

-- DROP TABLE public."Employee";

CREATE TABLE public."Employee"
(
  employee_id integer NOT NULL DEFAULT nextval('"Employee_employee_id_seq"'::regclass),
  employee_tfn character varying(50),
  employment_type integer,
  employment_pay integer,
  employee_maxhours integer,
  employee_join_date character varying(15),
  employee_left_date character varying(15),
  employee_identity_type integer,
  employee_identity_number character varying(50),
  user_id integer,
  CONSTRAINT "Employee_pkey" PRIMARY KEY (employee_id),
  CONSTRAINT "Employee_employment_type_fkey" FOREIGN KEY (employment_type)
      REFERENCES public."JobType" (job_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_user_id FOREIGN KEY (user_id)
      REFERENCES public.appuser (user_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public."Employee"
  OWNER TO roster;


-- Table: public."EmployeeLocation"

-- DROP TABLE public."EmployeeLocation";

CREATE TABLE public."EmployeeLocation"
(
  location_id integer,
  employee_id integer,
  CONSTRAINT fk_employee_id FOREIGN KEY (employee_id)
      REFERENCES public."Employee" (employee_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_location_id FOREIGN KEY (location_id)
      REFERENCES public."Location" (location_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public."EmployeeLocation"
  OWNER TO roster;


-- Table: public."JobType"

-- DROP TABLE public."JobType";

CREATE TABLE public."JobType"
(
  job_id integer NOT NULL DEFAULT nextval('"JobType_job_id_seq"'::regclass),
  job_name character varying(25),
  job_desc character varying(15),
  CONSTRAINT "JobType_pkey" PRIMARY KEY (job_id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public."JobType"
  OWNER TO roster;


-- Table: public."Location"

-- DROP TABLE public."Location";

CREATE TABLE public."Location"
(
  location_id integer NOT NULL DEFAULT nextval('"Location_location_id_seq"'::regclass),
  location_name character varying(25),
  location_desc character varying(15),
  client_id integer,
  CONSTRAINT "Location_pkey" PRIMARY KEY (location_id),
  CONSTRAINT fk_client_id FOREIGN KEY (client_id)
      REFERENCES public."Client" (client_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public."Location"
  OWNER TO roster;


-- Table: public."Payment"

-- DROP TABLE public."Payment";

CREATE TABLE public."Payment"
(
  payment_id integer NOT NULL DEFAULT nextval('"Payment_payment_id_seq"'::regclass),
  subcription_id integer,
  payment_amount double precision,
  payment_date character varying(15),
  payment_method integer,
  transaction_code character varying(100),
  CONSTRAINT "Payment_pkey" PRIMARY KEY (payment_id),
  CONSTRAINT fk_subscription_id FOREIGN KEY (subcription_id)
      REFERENCES public."Subscription" (subscription_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public."Payment"
  OWNER TO roster;

-- Table: public."Plan"

-- DROP TABLE public."Plan";

CREATE TABLE public."Plan"
(
  plan_id integer NOT NULL DEFAULT nextval('"Plan_plan_id_seq"'::regclass),
  plan_name character varying(50),
  plan_desc character varying(100),
  plan_price_monthly integer,
  plan_price_yearly integer,
  CONSTRAINT "Plan_pkey" PRIMARY KEY (plan_id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public."Plan"
  OWNER TO roster;


-- Table: public."Shift"

-- DROP TABLE public."Shift";

CREATE TABLE public."Shift"
(
  shift_id integer NOT NULL DEFAULT nextval('"Shift_shift_id_seq"'::regclass),
  employee_id integer,
  shift_date character varying(15),
  shift_start_time character varying(5),
  shift_end_time character varying(5),
  job_id integer,
  CONSTRAINT "Shift_pkey" PRIMARY KEY (shift_id),
  CONSTRAINT fk_employee_id FOREIGN KEY (employee_id)
      REFERENCES public."Employee" (employee_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_job_id FOREIGN KEY (job_id)
      REFERENCES public."JobType" (job_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public."Shift"
  OWNER TO roster;


-- Table: public."Subscription"

-- DROP TABLE public."Subscription";

CREATE TABLE public."Subscription"
(
  subscription_id integer NOT NULL DEFAULT nextval('"Subscription_subscription_id_seq"'::regclass),
  client_id integer,
  plan_id integer,
  subscription_price double precision,
  subscription_start_date character varying(15),
  subscription_status character varying(100),
  CONSTRAINT "Subscription_pkey" PRIMARY KEY (subscription_id),
  CONSTRAINT fk_client_id FOREIGN KEY (client_id)
      REFERENCES public."Client" (client_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_plan_id FOREIGN KEY (plan_id)
      REFERENCES public."Plan" (plan_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public."Subscription"
  OWNER TO roster;


-- Table: public.address

-- DROP TABLE public.address;

CREATE TABLE public.address
(
  address_id integer NOT NULL DEFAULT nextval('"Address_address_id_seq"'::regclass),
  address_line1 character varying(100),
  address_line2 character varying(100),
  address_city character varying(50),
  address_state character varying(50),
  address_country character varying(50),
  address_postcode character varying(6),
  CONSTRAINT "Address_pkey" PRIMARY KEY (address_id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.address
  OWNER TO roster;


-- Table: public.appuser

-- DROP TABLE public.appuser;

CREATE TABLE public.appuser
(
  user_id integer NOT NULL DEFAULT nextval('"User_user_id_seq"'::regclass),
  username character varying(16),
  pwd character varying(100),
  firstname character varying(50),
  lastname character varying(50),
  dob character varying(15),
  address_id integer,
  mobile character varying(15),
  account_expired boolean,
  account_locked boolean,
  enabled boolean,
  email character varying(256),
  CONSTRAINT "User_pkey" PRIMARY KEY (user_id),
  CONSTRAINT "User_address_id_fkey" FOREIGN KEY (address_id)
      REFERENCES public.address (address_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT "User_username_key" UNIQUE (username)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.appuser
  OWNER TO roster;
