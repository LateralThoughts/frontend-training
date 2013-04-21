describe("basic matchers - ", function() {

    var Rectangle = function (width, height) {
        this.width = width;
        this.height = height;
        this.shape = "rectangle";
    };
    Rectangle.prototype = {
        area: function() {
            return this.width * this.height;
        },
        perimeter: function() {
            return 2 * (this.width + this.height);
        },
        isTheAnswerToTheUltimateQuestionOfLifeTheUniverseAndEverything: function() {
            return this.perimeter() === 42;
        }
    };
    var Square = function(width) {
        this.width = width;
        this.height = width;
        this.shape = "square";
    }
    Square.prototype = Rectangle.prototype;

    it("should *strictly* check if the polygon is the answer to everything", function() {
        expect(new Rectangle(2, 8)
            .isTheAnswerToTheUltimateQuestionOfLifeTheUniverseAndEverything())
            .toBe(false);
    });

    it("should compute rectangle perimeter", function() {
        expect(new Rectangle(1,2).perimeter()).toEqual(6);
    });

    it("should consider existing properties as *defined*", function() {
        expect(new Square(2).width).toBeDefined();
    });

    it("should compare areas", function() {
        expect(new Square(2).area()).toBeLessThan(new Rectangle(3,2).area());
    });

    it("should match shape by the proper regex/description", function() {
        expect(new Square(2).shape).toMatch(/square/);
    });

    it("should detect exception-throwing function", function() {
        var zuprFunction = function() {
            return new Square(2).area() / new Square(side).perimeter();
        };
        expect(zuprFunction).toThrow();
    });

    it("should match anything", function() {
        expect([]).toEqual(jasmine.any(Array));
    })
});