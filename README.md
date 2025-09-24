# TMDB Search App
<img src="https://github.com/user-attachments/assets/65e7e5fc-15c0-4f68-93d6-29a580aa0759"
         alt="Screenshot 1"
         width="240" height="888">
<img src="https://github.com/user-attachments/assets/cca85024-607f-4403-84bd-0bef805857b4"
         alt="Screenshot 2"
         width="240" height="888">
<img src="https://github.com/user-attachments/assets/e03e0f20-9d34-4310-ada8-4f8891fbd057"
         alt="Screenshot 3"
         width="240" height="888">

### This Android app allows the user to search movies in The Movie Database by their title. It also displays details about any movie such as ratings, description and more. <br> <br>


## 💻 Implementation
- Kotlin + Jetpack Compose
- Based on MVI Architecture
- Pull-to-refresh functionality
- Infinite scroll support (with pagination)
- Unit Test for a ViewModel

## 📁 Structure
```bash
tmdbapp/
    graphics/        # The particle effect
    interactors/   
    models/   
    mvi/             # Definitions for basic MVI utilities (ViewModel etc.)
    network/         # API provider
    repository/    
    statics/         # Objects
    transformers/  
    ui/
        components/  # Various reusable components
        screens/
        theme/       
    utils/           # Various functional utilities
```

## 🛠️ Setup
1. Clone the repository
2. Add your API key in `local.properties`
```
TMDB_API_KEY=[YOUR_KEY]
```
[See how the app works](https://github.com/Stagnant09/TMDBApp/raw/refs/heads/master/TMDBApp3.mp4)
---
<a target="_blank" href="https://icons8.com/icon/AxHFXpfUuWsm/the-movie-database">The Movie Database</a> icon by <a target="_blank" href="https://icons8.com">Icons8</a>
