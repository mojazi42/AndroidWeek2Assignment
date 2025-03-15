# News Reader App

A news reader application built with **Jetpack Compose**, featuring **Dark Mode, Bookmarking, and Localization (English & Arabic)**. The app allows users to browse news articles, bookmark their favorites, and switch between light and dark themes.

## Project Overview
This project demonstrates **modern Android development** using Jetpack Compose and Material 3. The app includes:
- A **LazyColumn-based news feed** displaying static articles.
- **Navigation to a detailed news screen** when a news item is clicked.
- **Bookmarking functionality** with persistence.
- **Dark Mode toggle** to switch between themes.
- **Localization support for Arabic & English**, including **RTL layout support**.

---

##  Setup Instructions
### **Prerequisites**
Ensure you have the following installed:
- **Android Studio** (Giraffe or newer)
- **Kotlin 1.8+**
- **Jetpack Compose (latest stable version)**

### **How to Run the Project**
1. **Clone the repository**
   ```bash
   git clone https://github.com/YOUR_GITHUB_USERNAME/NewsReaderApp.git
Open the project in Android Studio
Sync Gradle files
Run the project on an emulator or a real device



 Features Implemented
1️ News Feed with LazyColumn
Displays 10 static news articles in a LazyColumn.
Clicking a news item navigates to the detailed news screen.
2️ News Detail Screen & Bookmarking
Displays the news title, content, and date.
Bookmark button allows users to save and remove news articles.
Persistent bookmarks using SharedPreferences to store saved articles.
3️ Dark Mode & Theming
Uses Material 3's MaterialTheme API for:
Typography (custom fonts & text styles)
ColorScheme (Light & Dark Mode support)
Theme Toggle Switch in the top bar.
4️ Localization & RTL Support
Supports both English & Arabic languages.
News content switches based on the selected language.
RTL Layout support: The UI switches to Right-to-Left (RTL) when Arabic is selected.
 Technologies Used
Jetpack Compose - UI Framework
Navigation Component - Screen navigation
Material 3 - Theming & UI styling
ViewModel - State management
SharedPreferences - Persistent bookmarking
Localization API - Multi-language support
RTL Support - Right-to-Left layout adjustments
 How to Contribute
We welcome contributions! Follow these steps:



Fork the repository
Create a new branch for your changes
git checkout -b feature-branch
Make your changes and commit
git commit -m "Added new feature"
Push the changes to your forked repository
git push origin feature-branch
Open a Pull Request in the original repository
