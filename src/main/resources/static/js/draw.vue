var data = { x: 0 , y: 0}

Vue.component('keyY', {
    template: '<button @click="y += 1">{{ y }}</button>',
    // 데이터는 기술적으로 함수이므로 Vue는 따지지 않지만
    // 각 컴포넌트 인스턴스에 대해 같은 객체 참조를 반환합니다.
    data: function () {
        return data.x;
    }
})

Vue.component('keyX', {
    template: '<button @click="x += 1">{{ x }}</button>',
    // 데이터는 기술적으로 함수이므로 Vue는 따지지 않지만
    // 각 컴포넌트 인스턴스에 대해 같은 객체 참조를 반환합니다.
    data: function () {
        return data.x;
    }
})

new Vue({
    el: '#example-2'
})