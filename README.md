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
- Invitations expire in 1 week. They are managed by a weekly scheduled job. Only one pending invitation can exist per user. A user can be reinvited if the invitation is expired. Users cannot be reinvited if the invitation is rejected.

## Organization
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

