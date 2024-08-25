# magerwa-cyangugu border
This projects facilitate to collect Arrival notes and their tallies from Cyangugu

The Cargo Arrival Management System is designed to streamline the process of recording and reporting the arrival of cargo, specifically focusing on tracking and tallying the total amount of kilograms (kgs) received by different tallies. The system provides comprehensive monthly reports summarizing the total kilograms received, categorized by each tally. The users of the system are divided into two roles: Admin and Tally.

User Roles:
Admin:

Manages users, including creating, updating, and deleting accounts for tallies.
Oversees the overall functionality of the system, ensuring data integrity and generating monthly reports.
Has access to all data within the system and can view detailed records of cargo arrivals, including per-tally breakdowns.
Tally:

Responsible for recording cargo arrival details, including:
Total kilograms received.
Truck details (e.g., truck number, driver name).
Custom operator details.
Can view their own recorded data and monitor the total kilograms they have received over time.
Submits the data to the system, which is then compiled into the monthly reports.
Features:
User Management:

Admins can create, edit, and delete Tally accounts.
Role-based access ensures that users only see the data relevant to their role.
Cargo Recording:

Tallies can input details about each cargo arrival, including the total kilograms received, truck details, and custom operator information.
The system automatically logs the date and time of each entry.
Monthly Reporting:

The system generates a detailed monthly report that summarizes the total kilograms received by each tally.
Reports are accessible to the Admin and can be exported in various formats (e.g., PDF, Excel).
Data Security:

Role-based access controls ensure that sensitive data is protected and only accessible to authorized users.
Audit logs track changes to data, ensuring transparency and accountability.
Technologies:
Backend: Java Spring Boot (for REST APIs)
Frontend: ReactJS or Angular (for user interface)
Database: MySQL or PostgreSQL (for data storage)
Authentication: Spring Security (for role-based access control)
Reporting: JasperReports or similar tools (for generating reports)
Use Cases:
A tally records the arrival of a cargo shipment, noting the total kilograms received and relevant details.
The admin views the monthly report to assess the total cargo processed by each tally.
The admin manages tally accounts, ensuring that only authorized users can record and view data.
This system provides an efficient and organized way to manage cargo arrivals and track the performance of tallies, ensuring accurate and timely reporting for operational decision-making.


