# Calculator in React


## Calculator App Setup

```bash
# initial setup command using typescript
# npx create-react-app calculator-app --template typescript

# start Java/Spring backend
cd calculator/calculator-java
./mvnw install
./mvnw spring-boot:run -pl application

# start React frontend
cd calculator/calculator-react/calculator-app
npm start
```

### How to Debug

* https://medium.com/@auchenberg/live-edit-and-debug-your-react-apps-directly-from-vs-code-without-leaving-the-editor-3da489ed905f


## Note about JAMStack 3 Types

* React plain app: SPA [this Calculator App is SPA]
* Next.js: SSR/SSG(v9.3+)
* Gatsby: SSG

### SPA vs SSR

* SSR (Server Side Rendering): for performance and SEO
  - https://qiita.com/G-awa/items/639f4f83aa4d97bc1f0d


## Gyp Trouble shooting

* gyp: No Xcode or CLT version detected!
  - https://qiita.com/baby-0105/items/18f6fbc073e160bf83ac
* Installation notes for macOS Catalina
  - https://github.com/nodejs/node-gyp/blob/master/macOS_Catalina.md


## Reference

* Tutorial: Intro to React
  - https://reactjs.org/tutorial/tutorial.html
