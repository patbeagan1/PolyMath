package main.dictionary

interface DictionaryMachineLearning {
    interface MachineLearningSymbols {

        // Common symbols
        val theta get() = "\\theta"
        val alpha get() = "\\alpha"
        val sigma get() = "\\sigma"
        val lambda get() = "\\lambda"
        val mu get() = "\\mu"

        // Symbols specific to Linear Regression
        val hTheta get() = "h_\\theta(x)"

        // Symbols for Logistic Regression
        val logit get() = "z"

        // Neural Networks symbols
        val reluFunction get() = "ReLU(x)"
        val tanhFunction get() = "\\tanh(x)"

        // SVM (Support Vector Machines) symbols
        val hingeLoss get() = "H(y)"

        // Decision Trees symbols
        val entropyFunction get() = "H(X)"
        val giniFunction get() = "I_G(p)"

        // PCA (Principal Component Analysis) symbols
        val principalComponent get() = "W^T X"
    }

//    interface MachineLearningFormulas : MachineLearningSymbols {
//
//
//        // Linear Regression (Cost Function)
//        val linearRegressionCost: String
//            get() = "J(\\theta) = \\frac{1}{2m} \\sum_{i=1}^{m} (h_\\theta(x^{(i)}) - y^{(i)})^2"
//
//        // Logistic Regression Sigmoid Function
//        val sigmoidFunction: String
//            get() = "\\sigma(z) = \\frac{1}{1 + e^{-z}}"
//
//        // Logistic Regression Cost Function (Log Loss)
//        val logisticRegressionCost: String
//            get() = "J(\\theta) = -\\frac{1}{m} \\sum_{i=1}^{m} [y^{(i)} \\log(h_\\theta(x^{(i)})) + (1-y^{(i)}) \\log(1-h_\\theta(x^{(i)}))]"
//
//        // Gradient Descent Update Rule
//        val gradientDescent: String
//            get() = "\\theta_j := \\theta_j - \\alpha \\frac{\\partial}{\\partial \\theta_j} J(\\theta)"
//
//        // Softmax Function
//        val softmaxFunction: String
//            get() = "\\sigma(z)_j = \\frac{e^{z_j}}{\\sum_{k=1}^{K} e^{z_k}}"
//
//        // Neural Network Activation Functions
//        val relu: String
//            get() = "f(x) = \\max(0, x)"
//
//        val tanh: String
//            get() = "f(x) = \\tanh(x) = \\frac{e^{x} - e^{-x}}{e^{x} + e^{-x}}"
//
//        // L1 Regularization (Lasso)
//        val l1Regularization: String
//            get() = "L1 = \\lambda \\sum_{i=1}^{n} |\\theta_i|"
//
//        // L2 Regularization (Ridge)
//        val l2Regularization: String
//            get() = "L2 = \\lambda \\sum_{i=1}^{n} \\theta_i^2"
//
//        // KMeans clustering objective function
//        val kMeansObjective: String
//            get() = "J = \\sum_{i=1}^{m} \\min_{\\mu_j \\in C} ||x^{(i)} - \\mu_j||^2"
//
//        // SVM (Support Vector Machines) Hinge Loss
//        val svmHingeLoss: String
//            get() = "H(y) = \\max(0, 1 - yf(x))"
//
//        // Decision Trees (Information Gain based on Entropy)
//        val informationGain: String
//            get() = "IG(E) = H(D) - \\sum_{k=1}^{m} \\frac{|D_k|}{|D|} H(D_k)"
//
//        // Entropy
//        val entropy: String
//            get() = "H(X) = - \\sum_{i=1}^{n} p(x_i) \\log_2 p(x_i)"
//
//        // Decision Trees (Gini Impurity)
//        val giniImpurity: String
//            get() = "I_G(p) = \\sum_{i=1}^{J} p(i) (1 - p(i))"
//
//        // PCA (Principal Component Analysis) objective
//        val pcaObjective: String
//            get() = "\\max_W Var(W^T X)"
//
//
//        // Linear Regression Cost Function
//        val linearRegressionCost: String
//            get() = "J($theta) = \\frac{1}{2m} \\sum_{i=1}^{m} ($hTheta - y^{(i)})^2"
//
//        // Logistic Regression Sigmoid Function
//        val sigmoidFunction: String
//            get() = "$sigma($logit) = \\frac{1}{1 + e^{-$logit}}"
//
//        // Neural Network Activation Functions
//        val relu: String
//            get() = "$reluFunction = \\max(0, x)"
//
//        val tanh: String
//            get() = "$tanhFunction = \\frac{e^{x} - e^{-x}}{e^{x} + e^{-x}}"
//
//        // SVM Hinge Loss
//        val svmHingeLoss: String
//            get() = "$hingeLoss = \\max(0, 1 - yf(x))"
//
//        // Decision Trees (Information Gain based on Entropy)
//        val informationGain: String
//            get() = "IG(E) = $entropyFunction - \\sum_{k=1}^{m} \\frac{|D_k|}{|D|} H(D_k)"
//
//        // Entropy
//        val entropy: String
//            get() = "$entropyFunction = - \\sum_{i=1}^{n} p(x_i) \\log_2 p(x_i)"
//
//        // Decision Trees (Gini Impurity)
//        val giniImpurity: String
//            get() = "$giniFunction = \\sum_{i=1}^{J} p(i) (1 - p(i))"
//
//        // PCA Objective
//        val pcaObjective: String
//            get() = "\\max_{$mu} Var($principalComponent)"
//    }
}