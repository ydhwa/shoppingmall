

-- Table OAUTH_CLIENT_DETAILS

DROP TABLE IF EXISTS oauth_client_details;

create table oauth_client_details (
  client_id               VARCHAR(255) PRIMARY KEY,
  resource_ids            VARCHAR(255),
  client_secret           VARCHAR(255),
  scope                   VARCHAR(255),
  authorized_grant_types  VARCHAR(255),
  web_server_redirect_uri VARCHAR(255),
  authorities             VARCHAR(255),
  access_token_validity   INTEGER,
  refresh_token_validity  INTEGER,
  additional_information  VARCHAR(4096),
  autoapprove             VARCHAR(255)
);




-- Table OAUTH_CLIENT_TOKEN

DROP TABLE IF EXISTS oauth_client_token;

create table oauth_client_token (
  token_id          VARCHAR(255),
  token             BYTEA,
  authentication_id VARCHAR(255) PRIMARY KEY,
  user_name         VARCHAR(255),
  client_id         VARCHAR(255)
);




-- Table OAUTH_ACCESS_TOKEN

DROP TABLE IF EXISTS oauth_access_token;

create table oauth_access_token (
  token_id          VARCHAR(255),
  token             BYTEA,
  authentication_id VARCHAR(255) PRIMARY KEY,
  user_name         VARCHAR(255),
  client_id         VARCHAR(255),
  authentication    BYTEA,
  refresh_token     VARCHAR(255)
);




-- Table OAUTH_REFRESH_TOKEN

DROP TABLE IF EXISTS oauth_refresh_token;
create table oauth_refresh_token (
  token_id VARCHAR(255),
  token BYTEA,
  authentication BYTEA
);




-- Table OAUTH_CODE

DROP TABLE IF EXISTS oauth_code;
create table oauth_code (
  code VARCHAR(255), authentication BYTEA
);


-- Table OAUTH_APPROVALS

DROP TABLE IF EXISTS oauth_approvals;
create table oauth_approvals (
	userId VARCHAR(255),
	clientId VARCHAR(255),
	scope VARCHAR(255),
	status VARCHAR(10),
	expiresAt TIMESTAMP,
	lastModifiedAt TIMESTAMP
);



select * from oauth_access_token;

-- customized oauth_client_details table
-- DROP TABLE IF EXISTS ClientDetails;
-- create table ClientDetails (
--  appId VARCHAR(255) PRIMARY KEY,
--  resourceIds VARCHAR(255),
--  appSecret VARCHAR(255),
--  scope VARCHAR(255),
--  grantTypes VARCHAR(255),
--  redirectUrl VARCHAR(255),
--  authorities VARCHAR(255),
--  access_token_validity INTEGER,
--  refresh_token_validity INTEGER,
--  additionalInformation VARCHAR(4096),
--  autoApproveScopes VARCHAR(255)
-- );

INSERT
INTO oauth_client_details (CLIENT_ID, RESOURCE_IDS, CLIENT_SECRET, SCOPE, AUTHORIZED_GRANT_TYPES, WEB_SERVER_REDIRECT_URI, AUTHORITIES, ACCESS_TOKEN_VALIDITY, REFRESH_TOKEN_VALIDITY, ADDITIONAL_INFORMATION, AUTOAPPROVE)
VALUES ('shoppingmall', '', '1234', 'MALL_CLIENT', 'password, authorization_code, refresh_token', '', '', null, null, '{}', '');
