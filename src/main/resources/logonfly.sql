  -- Table: public.tenant

-- DROP TABLE public.tenant;

CREATE TABLE public.tenant
(
  id serial NOT NULL,
  tenantname character varying(16),
  CONSTRAINT "tenant_pkey" PRIMARY KEY (id),
  CONSTRAINT "tenant_tenantname_key" UNIQUE (username)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.tenant
  OWNER TO roster;


-- Table: public.employee

-- DROP TABLE public.employee;

CREATE TABLE public.employee
(
  id serial NOT NULL,
  tenant_id integer,
  username character varying(256),
  pwd character varying(100),
  firstname character varying(50),
  lastname character varying(50),
  dob character varying(15),
  emp_address character varying(500),
  mobile character varying(15),
  email character varying(256),
  account_expired boolean,
  account_locked boolean,
  account_enabled boolean,
  employment_type integer DEFAULT 1,
  jobtype integer DEFAULT 1,
  emp_role integer DEFAULT 1,
  profile_pic character varying(256),
  CONSTRAINT employee_pkey PRIMARY KEY (id),
  CONSTRAINT employee_username_ukey UNIQUE (username),
  CONSTRAINT tenant_fk FOREIGN KEY (tenant_id)
      REFERENCES public.tenant (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT employee_email_pkey UNIQUE (email)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.employee
  OWNER TO roster;


