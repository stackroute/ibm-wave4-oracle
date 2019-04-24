FROM node:latest
RUN npm install -g @angular/cli
RUN mkdir -p /src
WORKDIR /src
COPY package.json /src/
RUN npm install
RUN npm cache verify
COPY . /src
EXPOSE 4200
#CMD ["ng","serve","--host=0.0.0.0"]
CMD ["ng" ,"serve" ,"--host=0.0.0.0"]
