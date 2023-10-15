# mars-exploration-2

## About The Project
Mars Exploration is one of the projects during Fullstack Dev course at Codecool. It's a pure Java application developed in 3 sprints by a team of three. It's a simulation that runs in steps until Mars gets fully collonized.

The first task was to generate a map of Mars which was to meet specific requirements such as the presence of defined minerals, their quantity and layout on the map.

After that the Mars surface was to be scanned by a deployed rover to find out weather it can be collonizable. The condition to collonize Mars is that the rover finds water. There might be different searching strategies as well as a limit of permitted movements.

The last step was Mars collonization. In that case, the rover should pick one reasonable spot nearby the resources it found and start extracting minerals to build a command center right there. Once the building is completed, the rover can focus on gathering minerals. The command center takes care of constructing subsequent rovers when enough minerals have been gathered for it. The goal of this center is to create enough rovers to cover all the available resources within its radius (it could be several mineral spots, also water or any other resource that was included during the previous sprint).

Once all these resources have an active rover assigned to it, so it can extract them and deliver them regularly to the command center, the final rover of this settlement is created. This rover will be deployed as an explorer and will attempt to find a new location suitable for a secondary base somewhere else. When it finds it, it follows the same strategy as the first rover deployed in the chart. The only difference is that once this secondary base has been completed and all its nearby resources are being extracted, then the simulation ends.

## Application Presentation:
Below you can find an outcome of the last step of the simulation
![image](https://github.com/CodecoolGlobal/mars-exploration-2-1q2023-java-ptatarczuk/assets/115543941/e416901b-8e7c-4661-affd-4da9d7f082fa)


## Built With
<a  href="https://www.java.com/"  title="Java"><img  src="https://github.com/get-icon/geticon/raw/master/icons/java.svg"  alt="Java"  width="50px"  height="50px"></a>
<a  href="https://www.jetbrains.com/idea/"  title="IntelliJ"><img  src="https://github.com/get-icon/geticon/raw/master/icons/intellij-idea.svg"  alt="IntelliJ"  width="50px"  height="50px"></a>

Other Technologies:

<a  href="https://discord.com/"  title="Discord"><img  src="https://github.com/get-icon/geticon/raw/master/icons/discord.svg"  alt="Discord"  width="50px"  height="50px"></a>
<a  href="https://git-scm.com/"  title="Git"><img  src="https://github.com/get-icon/geticon/raw/master/icons/git-icon.svg"  alt="Git"  width="50px"  height="50px"></a>
<a  href="https://github.com/ptatarczuk"  title="github"><img  src="https://github.com/ptatarczuk/Ideas/blob/main/server/images/github.svg"  alt="github"  width="50px"  height="50px"></a>
<a  href="https://trello.com/"  title="trello"><img  src="https://github.com/get-icon/geticon/raw/master/icons/trello.svg"  alt="trello"  width="50px"  height="50px"></a>


## Installation and Running the Application
### To run this application on your local machine, follow these steps:

  * Install Git:
If you haven't already, make sure to install Git, the version control system, on your computer.

  * Clone the Repository:
    
Copy the project's repository URL and clone it to your local machine using the following command in your terminal:

    git clone <repository-url>
    Replace <repository-url> with the actual URL of the project (you can use either HTTPS or SSH).

Open project in IDE, make sure that Maven dependencies have been updated.

Run the application.

## Authors
Jowita Druciak:

<a  href="https://github.com/"  title="github"><img  src="https://github.com/ptatarczuk/Ideas/blob/main/server/images/github.svg"  alt="github"  width="50px"  height="50px"></a><a  href="https://github.com/"  title="github"><img  src="https://github.com/get-icon/geticon/raw/master/icons/linkedin-icon.svg"  alt="github"  width="50px"  height="50px"></a> 

Aleksander Synoradzki:

<a  href="https://github.com/asynoradzki"  title="github"><img  src="https://github.com/ptatarczuk/Ideas/blob/main/server/images/github.svg"  alt="github"  width="50px"  height="50px"></a><a  href="https://github.com/asynoradzki"  title="github"><img  src="https://github.com/get-icon/geticon/raw/master/icons/linkedin-icon.svg"  alt="github"  width="50px"  height="50px"></a> 

Piotr Tatarczuk:

<a  href="https://github.com/ptatarczuk"  title="github"><img  src="https://github.com/ptatarczuk/Ideas/blob/main/server/images/github.svg"  alt="github"  width="50px"  height="50px"></a><a  href="https://github.com/ptatarczuk"  title="github"><img  src="https://github.com/get-icon/geticon/raw/master/icons/linkedin-icon.svg"  alt="github"  width="50px"  height="50px"></a> 

<p align="right">(<a href="#readme-top">back to top</a>)</p>
