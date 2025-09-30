# Learning Management System (LMS)

A comprehensive Learning Management System built with Spring Boot and modern web technologies to facilitate online education, course management, and institutional administration.

## 👥 User Roles

### 🎯 SuperAdmin
- Manage multiple institutions and their administrators
- Global view of all statistics, courses, and users
- System configuration and maintenance

### 👨‍💼 Admin
- Manage teachers, students, and courses within their institution
- Monitor attendance, schedules, and feedback
- Generate institutional reports

### 👩‍🏫 Teacher
- Create and manage courses, modules, and lessons
- Schedule and conduct online classes with Google Meet
- Mark student attendance and track progress
- Create and grade assignments
- Post announcements and course materials

### 🎓 Student
- Enroll in available courses
- Access course materials and submit assignments
- View class schedules and join online sessions
- Track personal progress and grades
- Provide course feedback

## 🚀 Features

### 📚 Course Management
- Hierarchical course structure (Course → Modules → Lessons)
- Support for video content and file uploads
- Flexible enrollment system (manual/auto)
- Course completion tracking

### 🧾 Assignment System
- Assignment creation with deadlines
- File upload and link submission
- Grading with feedback
- Submission history and tracking

### 🗓️ Scheduling System
- Weekly class scheduling
- Google Calendar integration
- Automated meeting link generation
- Calendar view for students and teachers

### ✅ Attendance Management
- Student attendance tracking (Present/Absent/Late)
- Teacher attendance monitoring
- Attendance reports and analytics

### 📣 Communication Tools
- Course announcements
- Email and SMS notifications
- In-app messaging
- Push notifications

### 📊 Analytics & Reporting
- Student performance tracking
- Course completion statistics
- Teacher performance metrics
- Custom report generation

## 🛠️ Technologies Used

### Backend
- Java 17
- Spring Boot 3.x
- Spring Security
- JPA/Hibernate
- Maven

### Frontend
- React.js
- Redux
- Material-UI
- Axios

### Database
- PostgreSQL
- Redis (Caching)

### Integrations
- Google Meet API
- Google Calendar API
- Jitsi Meet (Open Source Alternative)
- Keycloak (Authentication)
- Moodle (LMS Integration)

## 🏗️ System Architecture

### Project Structure
```
lms-learning-platform/
├── src/
│   └── main/
│       ├── java/com/lms/
│       │   ├── config/                      # Application configurations
│       │   ├── controller/                  # REST endpoints
│       │   ├── dto/                         # Data Transfer Objects
│       │   ├── entity/                      # JPA entities
│       │   ├── repository/                  # Data access layer
│       │   ├── service/                     # Business logic
│       │   └── security/                    # Authentication & Authorization
│       └── resources/                       # Static resources and configs
└── pom.xml
```

### Key DTOs
- `Auth`: `LoginRequest`, `RegisterRequest`, `AuthResponse`
- `Course`: `CourseDto`, `ModuleDto`, `LessonDto`
- `Enrollment`: `EnrollmentRequestDto`, `EnrollmentResponseDto`
- `Assignment`: `AssignmentDto`, `SubmissionDto`
- `Schedule`: `ScheduleDto`
- `Feedback`: `FeedbackDto`
- `Notification`: `NotificationDto`

## 🚀 Getting Started

### Prerequisites
- Java 17 or higher
- Maven 3.6+
- Node.js 16+
- PostgreSQL 13+
- Google Cloud Account (for Google APIs)

### Installation
1. **Clone the repository**
   ```bash
   git clone https://github.com/your-username/learning-management-system.git
   cd learning-management-system
   ```

2. **Configure the database**
   - Create a new PostgreSQL database
   - Update `application.properties` with your database credentials
   - Configure Google OAuth2 and Calendar API credentials

3. **Build and run the backend**
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

4. **Set up the frontend**
   ```bash
   cd frontend
   npm install
   npm start
   ```

5. **Access the application**
   - Frontend: http://localhost:3000
   - Backend API: http://localhost:8080
   - API Documentation: http://localhost:8080/swagger-ui.html

## 🤝 Contributing

We welcome contributions! Please follow these steps:
1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 📞 Contact

Project Link: [https://github.com/your-username/learning-management-system](https://github.com/your-username/learning-management-system)

## 🙏 Acknowledgments

- [Spring Boot](https://spring.io/projects/spring-boot)
- [React](https://reactjs.org/)
- [Material-UI](https://material-ui.com/)
- [Jitsi Meet](https://meet.jit.si/)
- [Keycloak](https://www.keycloak.org/)
- [Moodle](https://moodle.org/)

### User Management
- **Multi-role System**: Comprehensive role-based access control (Admin, Instructor, Student, Teaching Assistant)
- **Authentication**: Secure JWT-based authentication with email verification
- **Profile Management**: Customizable user profiles with avatars and personal information
- **Password Recovery**: Secure password reset flow with email verification
- **SSO Integration**: Support for Google, Microsoft, and other OAuth2 providers
- **User Groups**: Create and manage user groups for better organization

### Course Management
- **Course Creation**: Intuitive course creation wizard with rich text editing
- **Curriculum Builder**: Drag-and-drop course content organization
- **Prerequisites**: Set course prerequisites and completion requirements
- **Batch Processing**: Manage multiple courses and student enrollments in bulk
- **Scheduling**: Set course start/end dates and enrollment periods
- **Certification**: Generate and manage course completion certificates

### Content Management
- **Rich Media Support**: Embed videos, images, and interactive content
- **SCORM Compliance**: Import and export SCORM-compliant courses
- **Version Control**: Track and manage content versions
- **Content Locking**: Restrict access based on completion of previous modules
- **Offline Access**: Download course materials for offline study
- **Interactive Content**: Support for H5P interactive elements

### Assessment & Evaluation
- **Quiz Engine**: Multiple question types (MCQ, True/False, Essay, etc.)
- **Automated Grading**: Instant feedback for objective assessments
- **Rubric-Based Grading**: Create custom rubrics for subjective assessments
- **Plagiarism Detection**: Integrated tools to ensure academic integrity
- **Peer Review**: Enable peer assessment for assignments
- **Survey & Feedback**: Collect course and instructor feedback

### Communication Tools
- **Announcements**: Broadcast important updates to all enrolled users
- **Discussion Forums**: Threaded discussions with rich text support
- **Live Chat**: Real-time messaging between users
- **Video Conferencing**: Integrated BigBlueButton for live sessions
- **Notifications**: In-app, email, and mobile push notifications
- **Calendar**: Shared calendar for important dates and deadlines

### Analytics & Reporting
- **Progress Tracking**: Visual dashboards for student progress
- **Engagement Metrics**: Track user activity and participation
- **Custom Reports**: Generate and export detailed reports
- **Learning Analytics**: Identify at-risk students and learning patterns
- **Completion Tracking**: Monitor course and certification completion
- **Revenue Reports**: For paid courses and subscriptions

### E-commerce & Monetization
- **Course Marketplace**: Sell courses with integrated payment gateways
- **Subscription Plans**: Create different pricing tiers
- **Coupons & Discounts**: Run promotional campaigns
- **Affiliate Program**: Enable users to become affiliates
- **Tax & Payouts**: Handle taxes and instructor payouts

### Mobile & Accessibility
- **Responsive Design**: Fully responsive for all devices
- **Mobile App**: Native mobile applications for iOS and Android
- **Screen Reader Support**: WCAG 2.1 AA compliant
- **Multilingual Support**: RTL and multiple language support
- **Dark Mode**: Reduce eye strain with dark theme

### Integration & API
- **LTI Integration**: Seamless integration with other learning platforms
- **RESTful API**: Comprehensive API for custom integrations
- **Webhooks**: Real-time event notifications
- **Zapier/IFTTT**: Connect with thousands of apps
- **Single Sign-On (SSO)**: Enterprise authentication support
- **LMS Standards**: Support for xAPI (Tin Can), IMS Global standards

### Security & Compliance
- **GDPR Compliance**: Data protection and privacy controls
- **Data Encryption**: End-to-end encryption for sensitive data
- **Regular Backups**: Automated backup system
- **Compliance Reporting**: Generate compliance reports
- **Audit Logs**: Detailed logs of all system activities
- **Access Control**: Fine-grained permissions system

## 🛠️ Technologies Used

### Backend
- Java 17
- Spring Boot 3.x
- Spring Security
- JPA/Hibernate
- Maven

### Frontend
- React.js
- Redux
- Material-UI
- Axios

### Database
- PostgreSQL

### DevOps
- Docker
- GitHub Actions (CI/CD)

## 📦 Open Source Integrations

- **Moodle** - LTI integration for content sharing
- **BigBlueButton** - For live video conferencing
- **H5P** - Interactive content creation
- **Nextcloud** - File storage and sharing
- **Rocket.Chat** - Real-time communication

## 🚀 Getting Started

### Prerequisites
- Java 17 or higher
- Maven 3.6+
- Node.js 16+
- PostgreSQL 13+
- Docker (optional)

### Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/your-username/learning-management-system.git
   cd learning-management-system
   ```

2. **Configure the database**
   - Create a new PostgreSQL database
   Update `application.properties` with your database credentials

3. **Build and run the backend**
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

4. **Set up the frontend**
   ```bash
   cd frontend
   npm install
   npm start
   ```

5. **Access the application**
   - Frontend: http://localhost:3000
   - Backend API: http://localhost:8080

## 📝 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 🤝 Contributing

Contributions are what make the open-source community such an amazing place to learn, inspire, and create. Any contributions you make are **greatly appreciated**.

1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3. Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the Branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 📞 Contact

Project Link: [https://github.com/your-username/learning-management-system](https://github.com/your-username/learning-management-system)

## 🙏 Acknowledgments

- [Spring Boot](https://spring.io/projects/spring-boot)
- [React](https://reactjs.org/)
- [Material-UI](https://material-ui.com/)
- And all the amazing open-source libraries and frameworks that made this project possible
