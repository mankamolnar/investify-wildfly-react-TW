# Investify
## I. Integrating React
### 1. Configurate WildFly to serve static files  (css, js, images etc.)

a.) First go to your WildFly server's root folder!  
You can find it in your IntelliJ under: File > Settings > Application server

b.) Open standalone\configuration\standalone.xml.

c.) Find default server section in the file:<br />
><container>
    <server name="default-server">
        <http-listener name="default" socket-binding="http" redirect-socket="https" enable-http2="true"/>
        <https-listener name="https" socket-binding="https" security-realm="ApplicationRealm" enable-http2="true"/>
        <host name="default-host" alias="localhost">
            <location name="/" handler="welcome-content"/>
            <location name="/static" handler="staticHandler"/>
            <filter-ref name="server-header"/>
            <filter-ref name="x-powered-by-header"/>
        </host>
    </server>
    <servlet-container name="default">
        <jsp-config/>
        <websockets/>
    </servlet-container>
    <handlers>
        <file name="welcome-content" path="${jboss.home.dir}/welcome-content"/>
        <file name="staticHandler" path="C:\Users\flowerpower\Desktop\java\investify-wildfly-react-FutureBillionares\static"/>
    </handlers>
</container>

d.) As you can see probably  you have 1 location tag and 1 file (in handler) tag in your standalone.xml. Now your job will be to add new ones. Here are the examples how.  

Location example:
><c>
    <location name="/static" handler="staticHandler"> 
    1. /static is the url route
    2. staticHandler is the name of your file handler
</c>

File handler example:
><c>
    <file name="staticHandler" path="/path/to/your/project/static">
</c>

e.) Test it! Put an image file into your static folder. Run WildFly. Open localhost:port/static/yourimage.png in your browser!

d.) Buy a massive bag for your money! :) 

### 2. React's structure

0.) First you have to know that React is using ES6 which is a very fresh, smart and fancy version of JavaScript. The 
problem is that it's not supported by all browsers (those probably use ES5). So we need a tool which is able to convert
our ES6 JavaScript to ES5 JavaScript syntax. We will use 2 tools for it, Webpack & Babel!

There is one more problem. You cannot run Webpack & Babel in terminal / cmd on it's own. If you want to be able to participate in
 developing React you will have to install Node.js (the newest version with npm). With it you will be able to run Webpack from the terminal
 and compile the ES6 React.js source file to a MINIFIED ES5 JS! Cool ha?

a.) /webpack.config.js: This is a config file for webpack. It contains the path of the uncompiled React js and the path to the output file.

b.) /package.json: This is a config file for npm. It contains modules that we want to use for compiling.

c.) /src/resources/react: Folder of the uncompiled React JavaScript's (for developing).

d.) /static/: Folder of the compiled app (bundle.js).

### 3. Installing Node.js and Npm
a.) Install Node.js and Npm GLOBALLY: [link text itself]: https://docs.npmjs.com/getting-started/installing-node

b.) Be sure that npm has been added to your ENVIRONMENTAL VARIABLEs and you can use npm command in your terminal / cmd. ("npm -v" should return the version of npm)

c.) cd /to/your/project/root

d.) npm install  
(This will install LOCALLY node and npm modules into a folder in your project's root called node_modules. Don't worry, I gitignored :] )

e.) Now we can test our npm and webpack configs by hitting "npm run prod-build" 
><c>
    "Shiiiiiiit, all I see is redness on the screen!" Comment of the Author
</c>

+1) Now it probably compiled your React JSX to a bundle.js and bundle.js.map as well. Don't worry! I gitignored them!   
With the js.map the chrome console will be able show the error's place on an uncompiled readable
code even if it's running the minified unreadable version. Quite nice!

(I just realized that the extension for uncompiled react app is .jsx, probably I missed it sometimes, sorry for that! :] )

### 4. Integrate Npm into IntelliJ
a.) First go to edit configurations:  
![alt text](https://raw.githubusercontent.com/mankamolnar/investify-wildfly-react-FutureBillionares/feature/mankamolnar_integrate_react/Doc/01_edit_configurations.png "Edit configurations")    

b.) Then  go to Deployment > +. It will open a panel with possible scripts to run before Java compile.
![alt text](https://raw.githubusercontent.com/mankamolnar/investify-wildfly-react-FutureBillionares/feature/mankamolnar_integrate_react/Doc/02_add_panel.png "Choose npm script")

c.) Choose Npm Script ;)  

d.) Setup "npm run prod-build" command and hit OK!  
![alt text](https://raw.githubusercontent.com/mankamolnar/investify-wildfly-react-FutureBillionares/feature/mankamolnar_integrate_react/Doc/03_npm_script_add.png "Add npm script")

e.) Restart server.  
(Now its probably compiling your React.jsx then starting WildFly [and compiling Java code as well... so time to roll! ;) ])

## II. Start developing in React!
### 1. Create routes

0.) Yep, you heard well, we gonna handle routes on the client side. One of the biggest features of React that it's able 
handle routes. All of the /api routes will be handled on the SERVER side, and all of the routes that are related with 
loading in a page of HTML will be handled on the CLIENT SIDE.  

Separated and clean black magic :].

a.) Open /src/resources/react/router.jsx
><kep>
    // *** ROUTER ***
    ReactDOM.render((
        <Router history={browserHistory}>
            <Route path="/" component={Index} />
            <Route path="/login" component={Index} />
            <Route path="/logout" component={Index} />
            <Route path="/faq" component={Index} />
        </Router>
    ), document.getElementById('app'));
</kep>

b.) Here ReactDOM starts in #app div!

c.) Router xml structure: path -> url path, component -> {React.component.name}

### 2. Create pages

a.) open Index.jsx
><c>
    // *** PAGES ***
    class Index extends React.Component {
        constructor() {
            super();
            this.state = {loggedIn:false};
            this.changeState = this.changeState.bind(this)
        }
    
        changeState() {
            console.log(this.state.loggedIn);
            this.setState({loggedIn:!this.state.loggedIn});
        }
    
        render() {
            return (
                <NavBar loggedIn={this.state.loggedIn} changeState={this.changeState} />
            );
        }
    }
</c>

b.) This is the first time you can see the good side of using ES6! Here we use "class" to define classes we can easily use extending and it also has a constructor method! Do you remember defining class and construct like "var MyClass = function(prop1) { this.pop1 = prop1; };". NEVER AGAIN! :]  

c.) Every React.Component has render method as well. If any of the component's attributes changes it's state, React will change the content in the Virtual DOM, then compareing the Virtual DOM with the browser's DOM and changes the differences.  

The point of this whole stuff that Virtual DOM is MUCH MUCH FASTER than the Browser's. So by calculating the differences in React's Virtual DOM and passing only the theese changes we save a lot of time!  

DOM performance: n * n  
VIRTUAL DOM performance: n  
( ! ! ! )

### 3. React "elements"


