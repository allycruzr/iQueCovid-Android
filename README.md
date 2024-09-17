
# Android Native MVVM Practice - Room Architecture

## iQueCovid

### Overview
iQueCovid is an Android application designed to provide users with information related to COVID-19 testing. The app utilizes the MVVM architecture along with Room for local database management, allowing users to add, view, and manage their COVID-19 test results efficiently.

### Features
- **User-Friendly Interface**: Intuitive navigation through various screens for easy access to information.
- **Test Management**: Users can add new tests, view details, and manage existing test records.
- **Data Visualization**: Displays statistics related to COVID-19 cases, recoveries, and deaths.
- **Location Services**: Utilizes device location to provide relevant data based on the user's geographical area.
- **Dark Mode**: Automatically switches to dark mode based on battery levels.

### Architecture
The application follows the MVVM (Model-View-ViewModel) architecture pattern, ensuring a clear separation of concerns and enhancing maintainability.

### Components
- **Model**: Represents the data and business logic (e.g., `Test`, `TestResult`, `County`).
- **View**: The UI components that display data to the user (e.g., activities and fragments).
- **ViewModel**: Manages UI-related data in a lifecycle-conscious way (e.g., `MainViewModel`, `TestListViewModel`).

### Getting Started
To run the application locally, follow these steps:

1. **Clone the repository**:
   ```bash
   git clone https://github.com/seuusuario/iQueCovid.git
   ```

2. **Open the project in Android Studio**.

3. **Build the project** to download the necessary dependencies.

4. **Run the application** on an emulator or a physical device.

### Dependencies
- **AndroidX libraries**
- **Room** for database management
- **Retrofit** for network operations
- **Google Material Design** components

### Screenshots
![Screenshot 1](https://github.com/user-attachments/assets/be12d1db-d90f-4c10-8e49-edd7404c22b8)
![Screenshot 2](https://github.com/user-attachments/assets/91669b98-babf-4bd7-b62d-345e8136e7ec)
![Screenshot 3](https://github.com/user-attachments/assets/e0aa55d1-5759-47de-913a-f2249c4fd6e6)
![Screenshot 4](https://github.com/user-attachments/assets/ec0dc9f9-eab8-4f76-88b6-299acf5e2e89)

### Contributing
Contributions are welcome! Please open an issue or submit a pull request for any enhancements or bug fixes.

### License
This project is licensed under the MIT License - see the LICENSE file for details.

### Navigation Map
Below is the navigation map for the iQueCovid app:

![Navigation Map](https://user-images.githubusercontent.com/59263912/126847941-46293be3-eeea-494e-8a73-b38aa19f75ef.png)
