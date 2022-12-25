# User, Invitation, and Organization Microservice

This microservice provides APIs for managing users, invitations, and organizations.

## User
A User has the following attributes:

- Status: Enum [ACTIVE, PENDING, DEACTIVATED, INVITED, PASSIVE, DELETED]
- Full Name: Characters only
- Email
- Normalized Name: all lowercase, no language-specific and English-only characters

A user can be in multiple organizations. An email can be used only once to create a user.

The microservice provides the following additional endpoints for users:

- Return all organizations that a user belongs to
- Search by normalized name to return matching users
- Search by email to return a single user


## Invitation
An Invitation has the following attributes:

- User ID
- Invitation Message
- Status: Enum [ACCEPTED, REJECTED, PENDING, EXPIRED]
- Invitations expire in 1 week. They are managed by a weekly scheduled job. 
- Only one pending invitation can exist per user. 
- A user can be reinvited if the invitation is expired. 
- Users cannot be reinvited if the invitation is rejected.

## Organization

An Organization has the following attributes:

- Organization Name: Alphanumeric characters
- Normalized Organization Name: all lowercase, no language-specific and English-only characters, and numbers
- Registry Number: Alphanumeric characters
- Contact Email
- Year Founded
- Phone
- Company Size
- A registry number can be used only once to create an organization. An organization can have multiple users.

The microservice provides the following additional endpoints for organizations:

- Return all users under an organization
- Search by normalized name, year, company size to return matching organizations
- Search by registry number to return a single organization
- Running the Microservice
- To run the microservice, follow these steps:


## Endpoints 
### User

- POST /users: Creates a new user using the data provided in the request body.
- GET /users: Retrieves a list of all users.
- GET /users/{id}: Retrieves a specific user by their ID.
- PUT /users/{id}: Updates a specific user with the data provided in the request body.
- DELETE /users/{id}: Deletes a specific user by their ID.
- GET /users/organizations/{organizationId}: Retrieves a list of users associated with a specific organization, identified by its ID.
- GET /users/{userId}/organizations: Retrieves a list of organizations associated with a specific user, identified by their ID.
- GET /users/searchUserByNormalizedName: Searches for users by their normalized name. This endpoint requires a query parameter normalizedName in the request.
- GET /users/searchUserByEmail: Searches for a user by their email address. This endpoint requires a query parameter email in the request.

### Invitation

- POST /invitations: Creates a new invitation using the data provided in the request body.
- PUT /invitations/users/{userId}: Updates an invitation for a specific user, identified by their ID, using the data provided in the request body.
- GET /invitations: Retrieves a list of all invitations.
- DELETE /invitations/{id}: Deletes a specific invitation by its ID.

### Organization

- POST /organizations: Creates a new organization using the data provided in the request body.
- PUT /organizations/{id}: Updates a specific organization by its ID using the data provided in the request body.
- POST /organizations/{organizationId}/users/{userId}: Associates a specific user, identified by their ID, with a specific organization, identified by its ID.
- GET /organizations/searchOrganizations: Searches for organizations using the provided query parameters. This endpoint requires one or more of the following - query parameters in the request: normalizedName, year, and companySize.
- GET /organizations/{registryNumber}: Retrieves an organization by its registry number.
- GET /organizations: Retrieves a list of all organizations.
- DELETE /organizations/{id}: Deletes a specific organization by its ID.

