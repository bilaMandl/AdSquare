# 📢 AdSquare - Interactive Ad Placement Platform

A responsive and dynamic web application for placing and managing advertisements in designated screen areas. Designed for simplicity, speed, and interactivity.

---

## 🚀 Features

- 🔄 Four structured ad zones arranged in a circular layout  
- 🔐 Customer registration and login with email & password validation  
- 📤 Ad upload form including:  
  - Image upload with cropping and 16:9 aspect ratio  
  - Optional image rotation before submission  
  - Display package selection  
  - Display area selection  
- 🌈 Smooth animations with Framer Motion  
- 📡 Backend API built with Spring Boot  
- 🧠 Ad visibility depends on customer and selected area  

---

## 🧰 Tech Stack

### Frontend (React)

- ⚛️ React with functional components and hooks  
- 📦 Axios for API communication  
- 🔐 Context API for auth state management  
- 💫 Framer Motion for animation effects  
- ✂️ Image cropping via `react-easy-crop`  
- 🔁 Drag-and-drop with file validation  
- 🧪 Client-side validation for email and password

### Backend (Spring Boot)

- 🧩 Spring Boot with RESTful architecture  
- 🗃️ JPA with PostgreSQL for data persistence  
- 🧾 Multipart file upload support for ad images  
- 🧠 Business logic for auth, ad management, area targeting, and view tracking  
- 🛡️ Input validation and basic security layers  
- 🎯 Area-specific ad handling logic

---

## ⚙️ Getting Started

### Run the backend

```bash
cd server-side
./mvnw spring-boot:run
```

### Run the frontend

```bash
cd client
npm install
npm start
```

---

## 🎨 Unique Experience

AdSquare combines simplicity and modern design by allowing users to view available ad zones, log in or register through a floating modal interface, and upload customized ads to specific screen quadrants — all in one fluid flow.

The user can crop and rotate the image directly in the browser before upload, ensuring consistent display and flexibility.

---

## 📌 Future Improvements

- Ad click/view tracking and analytics  
- Option to delete or update an existing ad  
- Admin panel for monitoring uploads and users  

---
