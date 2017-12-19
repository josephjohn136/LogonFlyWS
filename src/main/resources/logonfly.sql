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
  username character varying(16),
  tenant_id integer,
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
  employment_type bit,
  jobtype bit,
  emp_role bit,
  CONSTRAINT "employee_pkey" PRIMARY KEY (id),
  CONSTRAINT "employee_username_key" UNIQUE (username),
  CONSTRAINT fk_tenant_id FOREIGN KEY (tenant_id)
      REFERENCES public.tenant (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION

)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.employee
  OWNER TO roster;

