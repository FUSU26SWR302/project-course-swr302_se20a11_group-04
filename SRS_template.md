# Office Maintenance Management System
# Software Requirement Specification (SRS)

---

# 1. Introduction

## 1.1 Purpose

The Office Maintenance Management System is designed to help organizations manage office maintenance activities efficiently. The system allows employees to report equipment issues, managers to monitor maintenance activities, and technicians to perform maintenance tasks effectively.

---

## 1.2 Objectives

The objectives of the system are:

- Improve office maintenance efficiency
- Reduce equipment downtime
- Simplify maintenance scheduling
- Track maintenance history
- Improve communication between employees and technicians
- Support AI and IoT integration for smart maintenance

---

## 1.3 Project Overview

The system provides a centralized platform for office maintenance management. Users can report equipment problems, schedule maintenance tasks, monitor maintenance progress, and manage office assets.

The project also focuses on Research Based Learning (RBL), including:
- AI predictive maintenance
- IoT monitoring systems
- Smart office technologies

---

# 2. Scope

The Office Maintenance Management System supports:

- Equipment management
- Maintenance scheduling
- Fault reporting
- Technician assignment
- Notification system
- Maintenance history tracking
- AI predictive maintenance
- IoT monitoring integration

Target users include:
- Employees
- Maintenance technicians
- Managers
- Administrators

---

# 3. Existing Systems

## 3.1 IBM Maximo

IBM Maximo is an enterprise asset management system used for maintenance scheduling and asset management.

### Advantages
- Powerful enterprise management
- Advanced maintenance tracking
- Large-scale asset management

### Disadvantages
- High implementation cost
- Complex configuration
- Difficult for small organizations

---

## 3.2 UpKeep

UpKeep is a cloud-based CMMS platform designed for maintenance management.

### Advantages
- User-friendly interface
- Mobile application support
- Easy maintenance scheduling

### Disadvantages
- Limited AI integration
- Some advanced features require paid plans

---

## 3.3 Fiix CMMS

Fiix CMMS provides cloud maintenance management solutions with analytics features.

### Advantages
- Easy deployment
- Maintenance analytics
- Cloud-based management

### Disadvantages
- Limited customization
- Premium features require subscription

---

# 4. Business Problems

Organizations often face many maintenance-related problems such as:

- Delayed maintenance activities
- Lack of maintenance tracking
- Equipment downtime
- Poor communication between departments
- Difficulty monitoring equipment condition
- Lack of predictive maintenance solutions

The proposed system aims to solve these problems through centralized management and smart technologies.

---

# 5. Proposed Solution

The proposed solution is a web-based Office Maintenance Management System that supports:

- Equipment management
- Maintenance scheduling
- Fault reporting
- Technician assignment
- Notification management
- AI-based predictive maintenance
- IoT monitoring dashboard

---

# 6. User Roles

## 6.1 Employee

Employees can:
- Report equipment problems
- View maintenance status
- Receive notifications

---

## 6.2 Technician

Technicians can:
- View assigned tasks
- Update maintenance progress
- Record repair history

---

## 6.3 Manager

Managers can:
- Monitor maintenance activities
- Assign technicians
- View reports and statistics

---

## 6.4 Administrator

Administrators can:
- Manage users
- Manage system settings
- Manage permissions

---

# 7. Functional Requirements

The system shall provide the following functionalities:

- User authentication and authorization
- Equipment management
- Maintenance scheduling
- Fault reporting
- Technician assignment
- Notification system
- Maintenance history tracking
- Dashboard and reports
- AI maintenance prediction
- IoT monitoring integration

---

# 8. Non-functional Requirements

- The system should support multiple users simultaneously.
- The system should provide secure authentication.
- The system should have responsive UI design.
- The system should support cloud deployment.
- The system should ensure data consistency and reliability.
- The system should provide good performance and scalability.

---

# 9. Technologies

## Frontend
- ReactJS

## Backend
- Spring Boot

## Database
- MySQL

## Research Technologies
- AI Predictive Maintenance
- IoT Monitoring
- ESP32 Sensors

---

# 10. Research Based Learning (RBL)

This project focuses on several research areas:

## AI Predictive Maintenance
Research on machine learning algorithms used to predict equipment failures before breakdown occurs.

## IoT Monitoring
Research on ESP32 sensors and real-time monitoring systems for smart office environments.

## Smart Office Technologies
Research on intelligent office management and automation systems.

## System Architecture
Research on scalable web application architecture using Spring Boot and ReactJS.

---

# 11. Future Development

Future improvements may include:

- Mobile application support
- Advanced AI prediction models
- Real-time IoT analytics
- Smart building integration
- Voice assistant integration
- Cloud-based maintenance analytics


---


SOFTWARE REQUIREMENTS SPECIFICATION (SRS)
SMART OFFICE & ASSET MANAGEMENT SYSTEM
1. INTRODUCTION
1.1. Project Background (Based on Papers 1, 5, 6, 9)
In the era of digital transformation and the rapid development of Smart Office environments, facility, equipment, and corporate asset management play a critical role in organizational operational efficiency. Through actual surveys and theoretical research on modern complex management systems (Kumari et al., 2024; Himel et al., 2025), traditional asset management processes in many offices today face several major bottlenecks:
•	Tracking equipment and asset history manually via paper logs or fragmented Excel spreadsheets leads to human errors during manual entry, wastes a large amount of time for information retrieval, and lacks real-time data updates.
•	The reporting and handling process for hardware or infrastructure failures (e.g., furniture, computers, electrical equipment) is often delayed and lacks a scientific triaging system similar to emergency response models (Witter et al., 2023), resulting in operational downtime and a direct negative impact on employee productivity.
Therefore, building a centralized management software platform that utilizes identification technologies and real-time data synchronization is extremely critical to optimize operational costs and enhance office infrastructure sustainability.
1.2. System Objectives
•	To digitize 100% of the office asset lifecycle, from procurement, allocation, and maintenance to disposal.
•	To automate the process of reporting incidents and tracking the resolution progress of equipment malfunctions.
•	To provide interactive data visualization tools that assist office managers in making accurate, predictive maintenance decisions.
2. PRODUCT SCOPE
Based on the modular architecture design philosophy (Ostermaier-Welz et al., 2025), the system focuses on developing a cross-platform solution comprising a Web Application (for Administrators/Managers) and a Mobile Application (for Employees/Technicians) with the following core modules:
2.1. Asset Identification and Tracking Module (QR Code Integration - Based on Paper 6)
•	Unique QR Code Generation: The system automatically generates a unique QR code containing asset metadata (ID, asset name, category, purchase date, current location/department, status) whenever a new asset is registered.
•	Rapid Scan Inventory Control: Integrates the smartphone camera API via the mobile application, allowing staff to quickly scan QR codes to retrieve equipment details or perform periodic physical asset audits without manual typing.
2.2. Incident Reporting and Management Module (User-Centered Design - Based on Papers 9, 10)
•	Minimalist Ticket Form (UCD): Employs a user-friendly form design for reporting office damage. Users can submit an incident ticket via the Mobile App by scanning the QR code attached to the faulty asset, choosing the multi-tiered error type, and uploading images with just a few taps.
•	Automated Triaging Engine: Automatically calculates risk scores and categorizes the urgency level of incidents (e.g., Emergency -> Resolve immediately; Normal -> Queue-based processing) based on the asset category and the severity of the location affected.
•	Memory-Inspired Solution Suggestion (Immune System Analogy - Based on Paper 8): The system stores historical "Symptom -> Successful Fix" records in a localized database to automatically suggest troubleshooting procedures to technicians when a similar error recurs.
2.3. Real-Time Monitoring and Analytics Module (Dashboard Architecture - Based on Papers 1, 5)
•	Interactive Executive Dashboard: Displays real-time data charts regarding total asset count, current equipment status (In Use, Broken, Under Repair), and the trend lines of incident frequencies over time.
•	Asset Health Index Monitoring: Aggregates historical maintenance data to assess the efficiency and reliability of each asset category, triggering early warnings when equipment approaches its maintenance threshold, shifting the system from reactive to predictive maintenance.
2.4. Core Operation and Support Features (Based on Papers 5, 7)
•	Role-Based Authentication and Authorization: Restricts system access based on three main user roles: Office Employees (Create and submit tickets), Maintenance Technicians (Accept and resolve tickets), and System Administrators/Admin (Oversee general operations).
•	Push Notification System: Sends real-time push notifications to available technicians whenever a new incident is reported, and automatically updates the reporting employee on the resolution progress.
3. CURRENT SYSTEMS
To clarify the innovations and improvements of the proposed system, the project analyzes and contrasts it with existing solutions in the market:
3.1. Manual Logging (Paper Ledger / Standalone Microsoft Excel Sheets)
•	Advantages: Zero initial software procurement cost; requires no advanced technological infrastructure; easy to set up for small operations.
•	Disadvantages: Static data that is highly prone to discrepancies, duplication, or physical loss. It lacks real-time workflow tracking, meaning employees have no visibility into when their reported issues will be fixed, and managers lack an aggregated overview of equipment lifecycle costs.
3.2. Traditional Industrial Barcode Systems
•	Advantages: Provides a much higher accuracy rate than manual data entry; highly standardized across legacy logistics and warehousing setups.
•	Disadvantages: Requires a high upfront investment in specialized handheld barcode scanners, leading to significant infrastructure costs. Barcodes have very limited data storage capacity (restricted to short numeric sequences) and are easily damaged or scratched, rendering them unscannable. The associated software packages are often outdated and suffer from a lack of data interoperability across corporate departments.
3.3. Proposed Solution: Smart Office & Asset Management System
The proposed system integrates cutting-edge methodologies extracted from recent scientific literature (2023–2025) to deliver significant improvements over legacy systems:
•	High Flexibility and Cost-Effectiveness: Leverages QR code technology which possesses high error-correction capabilities (remains scannable even if up to 30% of the area is obscured or smudged) and utilizes employees' existing smartphones, completely removing the hardware cost of dedicated scanners.
•	Modern Multi-Tier Software Architecture: Combines a web-based management portal with a cross-platform mobile app, synced through a real-time cloud database to ensure instantaneous information flow between employees, technicians, and managers.
•	Intelligent Automation and UCD: Employs a User-Centered Design to streamline ticket creation, automated triaging algorithms, and memory-based solution indexing, dramatically reducing total maintenance response time compared to traditional, passive data trackers.

