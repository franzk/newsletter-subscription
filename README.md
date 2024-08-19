# SIMPLE NEWSLETTER SUBSCRIPTION TOOL (VUEJS + SPRING BOOT + RECAPTCHA)

<img src="https://img.shields.io/badge/Vue.js%203-35495E?style=for-the-badge&logo=vuedotjs&logoColor=4FC08D&style=flat-square"><img src="https://shields.io/badge/TypeScript-3178C6?logo=TypeScript&logoColor=FFF&style=flat-square"> <img src="https://img.shields.io/badge/pnpm-F69220?logo=pnpm&logoColor=fff"> - 
<img src="https://img.shields.io/badge/-JAVA%2017-00A7BB?style=for-the-badge&logo=java&logoColor=white&style=flat-square">
<img src="https://img.shields.io/badge/-SPRING%20BOOT%203.3.2-6eb442?style=for-the-badge&logo=spring&logoColor=white&style=flat-square"> <img src="https://img.shields.io/badge/-GRADLE%208.8-black?style=for-the-badge&logo=gradle&logoColor=white&style=flat-square"> - 
<img src="https://img.shields.io/badge/postgresql-4169e1?style=for-the-badge&logo=postgresql&logoColor=white&style=flat-square"> - 
<img src="https://img.shields.io/badge/-DOCKER-2496ed?style=for-the-badge&logo=docker&logoColor=white&style=flat-square"> - 
<img src="https://github.com/user-attachments/assets/95d40fa3-ee3d-4bb7-a976-2e0a239dd6d1" width="30"> V2


## Installation

### 1. clone this repository
```console 
git clone https://github.com/franzk/newsletter-subscription.git
```

### 2. Set up Google Recaptcha : 
- Create a reCAPTCHA v2 key. See: https://cloud.google.com/recaptcha/docs/create-key-website (Create checkbox reCAPTCHA keys)
- Rename the `front/.env.dist` file to `front/.env`
- Copy the reCAPTCHA key into the `front/.env` file


### 3. Start the backend using Docker Compose:
```console 
cd docker
docker compose --profile dev up
```

### 4. Set up VueJS :  
Navigate to the front-end directory, install dependencies, and run the development server:
```console 
cd ../front
pnpm install
pnpm run dev
```

### 5. Test the Application
Open your browser and go to http://localhost:8080.

###6. Deploy
```console 
cd docker
docker compose --profile prod up
```
Test it on : http://localhost
