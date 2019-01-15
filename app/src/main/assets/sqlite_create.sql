BEGIN;


CREATE TABLE activity (
    id integer NOT NULL,
    name character varying(255) NOT NULL,
    short_description character varying(255) NOT NULL,
    description character varying(255),
    location character varying(255) NOT NULL,
    price numeric(10,2),
    person_id integer NOT NULL,
    activity_type integer,
    date timestamp without time zone NOT NULL,
    image_id integer,
    status character(1) DEFAULT 'S' NOT NULL,
    image character varying(255),
    activity_image_id integer,
    person integer
);



--
-- Name: COLUMN activity.status; Type: COMMENT; Schema: public; Owner: postgres
--



--
-- Name: activity_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--


--
-- Name: activity_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--


--
-- Name: activity_type; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE activity_type (
    id integer NOT NULL,
    name character varying(255) NOT NULL
);


--
-- Name: image; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE image (
    id integer NOT NULL,
    name text,
    image bytea,
    imageid bytea,
    image_activity_id integer
);



--
-- Name: login; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE login (
    id integer NOT NULL,
    person_id integer NOT NULL,
    login_name character varying(50) NOT NULL,
    password character varying(255) NOT NULL
);



--
-- Name: person; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE person (
    id integer NOT NULL,
    first_name character varying(255) NOT NULL,
    last_name character varying(255) NOT NULL,
    email character varying(255) NOT NULL,
    age date,
    person_type_id integer NOT NULL,
    image_id integer,
    login_id integer
);




CREATE TABLE person_activity_comment (
    id integer NOT NULL,
    comment character varying(1500) NOT NULL,
    person_id integer NOT NULL,
    activity_id integer NOT NULL
);





--
-- Name: person_preference; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE person_preference (
    id integer NOT NULL,
    person_id integer NOT NULL,
    activity_type_id integer NOT NULL
);



CREATE TABLE person_type (
    id integer NOT NULL,
    name character varying(255) NOT NULL
);



CREATE TABLE reaction (
    id integer NOT NULL,
    reaction character(1) NOT NULL,
    person_id integer NOT NULL,
    activity integer NOT NULL,
    activity_reaction_id integer,
    person_reaction_id integer
);


END;