# Fetch Coding Exercise
## Introduction
This project was set up using Jetpack Compose with MVI architecture. 
The DI used is Koin 
The network calls are made using Retrofit & GSON

There are a total of 5 different modules to promote clean architecture, scalability, and separation of concerns
    
    1. Base Module
        - this module contains all the base classes/interfaces that other modules will rely on and use,
            all shared code between modules will go into this module
        - has no dependency on any other module

    2. Data Module
        - this module contains all the API call endpoints, API models, Mappers classes that map the API Models to Domain models,
            and Usecase and Repository Implementations
        - this module is not exposed to any of the UI modules since the UI should not care how network API calls and API data are 
            being implemented and manipulated for the UI
        - has dependancy on base and domain modules
        Network Folder - contains API URLs and API models 
        Mapper Folder - Mappers that map API Models to Domain Models
        Repository Folder - Classes (usually based off feature) that are in charge of calling the API and using mappers to map the data to domain.
                            Usually repository only contain the API endpoints that are relevant to its feature
        Usecase Folder - Contains all the usecases where each one call one function in a repository to get the data to return to the UI
                        usecases expose only one endpoint to the UI at a time 

    3. Domain Module 
        - this module contains all the Domain Models that the UI uses, Usecase interfaces, and Repository interfaces
        - this module is exposed to the UI modules. UI modules references the interfaces of usecases to get the data it requires
        - has dependancy on base

    4. Modules module
        - this modules contains all the Koin DI Modules for the Network, Mappers, Repositorys, and Usecases classes and infterfaces
        - this module has dependency on both domain and data modules since both the interface and implementation need to be referenced in the DI declaration
        - this module is exposed to App Module so Koin has reference to the on Application launch

    5. App module
        - this module contains all the UI with MVI architecture.
        - has dependency on Base, Modules, and domain
        - Simple MVI breakdown
            View - Viewmodel - Contract
            * each view has a viewmodel and contract 
            * views - written in Jetpack Compose
            * viewmodels - extend the ComposeViewModel in Base Module
            * contract - contains the Event and State class the the view and viewmodel use function, 
                        views send viewmodels Events, which the viewmodel then consumes and updates the State which updates the UI


