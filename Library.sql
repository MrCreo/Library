PGDMP  (    7            	    {            Library    16.0    16.0     �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            �           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            �           1262    16397    Library    DATABASE     }   CREATE DATABASE "Library" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'Russian_Russia.1251';
    DROP DATABASE "Library";
                postgres    false            �            1259    16524    book    TABLE     �   CREATE TABLE public.book (
    id bigint NOT NULL,
    author character varying(255),
    name character varying(255),
    year bigint
);
    DROP TABLE public.book;
       public         heap    postgres    false            �            1259    16523    book_id_seq    SEQUENCE     t   CREATE SEQUENCE public.book_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 "   DROP SEQUENCE public.book_id_seq;
       public          postgres    false    216            �           0    0    book_id_seq    SEQUENCE OWNED BY     ;   ALTER SEQUENCE public.book_id_seq OWNED BY public.book.id;
          public          postgres    false    215            �            1259    16533    book_registration    TABLE     �   CREATE TABLE public.book_registration (
    id bigint NOT NULL,
    book_id bigint,
    date timestamp without time zone,
    person_id bigint,
    status integer
);
 %   DROP TABLE public.book_registration;
       public         heap    postgres    false            �            1259    16532    book_registration_id_seq    SEQUENCE     �   CREATE SEQUENCE public.book_registration_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 /   DROP SEQUENCE public.book_registration_id_seq;
       public          postgres    false    218            �           0    0    book_registration_id_seq    SEQUENCE OWNED BY     U   ALTER SEQUENCE public.book_registration_id_seq OWNED BY public.book_registration.id;
          public          postgres    false    217            �            1259    16540    person    TABLE     �   CREATE TABLE public.person (
    id bigint NOT NULL,
    birth_date timestamp without time zone,
    f character varying(255),
    i character varying(255),
    o character varying(255)
);
    DROP TABLE public.person;
       public         heap    postgres    false            �            1259    16539    person_id_seq    SEQUENCE     v   CREATE SEQUENCE public.person_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 $   DROP SEQUENCE public.person_id_seq;
       public          postgres    false    220            �           0    0    person_id_seq    SEQUENCE OWNED BY     ?   ALTER SEQUENCE public.person_id_seq OWNED BY public.person.id;
          public          postgres    false    219            $           2604    16527    book id    DEFAULT     b   ALTER TABLE ONLY public.book ALTER COLUMN id SET DEFAULT nextval('public.book_id_seq'::regclass);
 6   ALTER TABLE public.book ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    215    216    216            %           2604    16536    book_registration id    DEFAULT     |   ALTER TABLE ONLY public.book_registration ALTER COLUMN id SET DEFAULT nextval('public.book_registration_id_seq'::regclass);
 C   ALTER TABLE public.book_registration ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    218    217    218            &           2604    16543 	   person id    DEFAULT     f   ALTER TABLE ONLY public.person ALTER COLUMN id SET DEFAULT nextval('public.person_id_seq'::regclass);
 8   ALTER TABLE public.person ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    220    219    220            �          0    16524    book 
   TABLE DATA           6   COPY public.book (id, author, name, year) FROM stdin;
    public          postgres    false    216          �          0    16533    book_registration 
   TABLE DATA           Q   COPY public.book_registration (id, book_id, date, person_id, status) FROM stdin;
    public          postgres    false    218   5       �          0    16540    person 
   TABLE DATA           9   COPY public.person (id, birth_date, f, i, o) FROM stdin;
    public          postgres    false    220   R       �           0    0    book_id_seq    SEQUENCE SET     :   SELECT pg_catalog.setval('public.book_id_seq', 1, false);
          public          postgres    false    215            �           0    0    book_registration_id_seq    SEQUENCE SET     G   SELECT pg_catalog.setval('public.book_registration_id_seq', 1, false);
          public          postgres    false    217            �           0    0    person_id_seq    SEQUENCE SET     <   SELECT pg_catalog.setval('public.person_id_seq', 1, false);
          public          postgres    false    219            (           2606    16531    book book_pkey 
   CONSTRAINT     L   ALTER TABLE ONLY public.book
    ADD CONSTRAINT book_pkey PRIMARY KEY (id);
 8   ALTER TABLE ONLY public.book DROP CONSTRAINT book_pkey;
       public            postgres    false    216            *           2606    16538 (   book_registration book_registration_pkey 
   CONSTRAINT     f   ALTER TABLE ONLY public.book_registration
    ADD CONSTRAINT book_registration_pkey PRIMARY KEY (id);
 R   ALTER TABLE ONLY public.book_registration DROP CONSTRAINT book_registration_pkey;
       public            postgres    false    218            ,           2606    16547    person person_pkey 
   CONSTRAINT     P   ALTER TABLE ONLY public.person
    ADD CONSTRAINT person_pkey PRIMARY KEY (id);
 <   ALTER TABLE ONLY public.person DROP CONSTRAINT person_pkey;
       public            postgres    false    220            -           2606    16548 -   book_registration fk7w4sqdp1k61nbmxutpkubj8fm    FK CONSTRAINT     �   ALTER TABLE ONLY public.book_registration
    ADD CONSTRAINT fk7w4sqdp1k61nbmxutpkubj8fm FOREIGN KEY (book_id) REFERENCES public.book(id) ON DELETE CASCADE;
 W   ALTER TABLE ONLY public.book_registration DROP CONSTRAINT fk7w4sqdp1k61nbmxutpkubj8fm;
       public          postgres    false    216    4648    218            .           2606    16553 -   book_registration fkr5ki3dppjwxmr5fa64ityd56l    FK CONSTRAINT     �   ALTER TABLE ONLY public.book_registration
    ADD CONSTRAINT fkr5ki3dppjwxmr5fa64ityd56l FOREIGN KEY (person_id) REFERENCES public.person(id) ON DELETE CASCADE;
 W   ALTER TABLE ONLY public.book_registration DROP CONSTRAINT fkr5ki3dppjwxmr5fa64ityd56l;
       public          postgres    false    218    4652    220            �      x������ � �      �      x������ � �      �      x������ � �     