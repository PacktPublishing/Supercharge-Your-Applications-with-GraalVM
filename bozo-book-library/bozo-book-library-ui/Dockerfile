# build environment
FROM node:13.12.0-alpine as build
WORKDIR /app
ENV PATH /app/node_modules/.bin:$PATH
COPY package.json ./
COPY package-lock.json ./
RUN npm ci --silent
RUN npm install react-scripts@3.4.1 -g --silent
COPY . ./

ARG REACT_APP_BOOK_LIB_SERVICE_URL
ENV REACT_APP_BOOK_LIB_SERVICE_URL=$REACT_APP_BOOK_LIB_SERVICE_URL

ARG REACT_APP_BOOK_INFO_SERVICE_URL
ENV REACT_APP_BOOK_INFO_SERVICE_URL=$REACT_APP_BOOK_INFO_SERVICE_URL

RUN npm run build

# production environment
FROM nginx:stable-alpine
COPY --from=build /app/build /usr/share/nginx/html
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]